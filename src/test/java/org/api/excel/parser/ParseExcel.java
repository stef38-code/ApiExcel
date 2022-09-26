package org.api.excel.parser;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.api.excel.annotations.ExcelSheet;
import org.api.excel.file.Excel;
import org.api.excel.file.ExcelException;
import org.api.excel.mapping.ModelMapper;
import org.api.excel.converter.RowConverter;
import org.api.excel.model.CellModel;
import org.api.excel.model.SheetModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class ParseExcel<T> {
    private static final Logger log = LoggerFactory.getLogger(ParseExcel.class);
    private final List<T> entities;

    private ParseExcel(Builder builder) {
        this.entities = builder.listEntities;
    }

    public static <T> Builder clazz(Class<T> clazz) {
        return new Builder<T>().clazz(clazz);
    }

    private List<T> getEntities() {
        return this.entities;
    }

    public static final class Builder<T> {
        private List<T> listEntities;
        private T instance;
        private Class<T> tClass;
        private List<String> files;

        private Builder() {
            this.files = new ArrayList<>();
        }

        public Builder<T> clazz(Class<T> obj) {
            this.tClass = obj;
            return this;
        }

        public Optional<List<T>> build() {
            traitement();
            List entities = new ParseExcel(this).getEntities();
            return Optional.ofNullable(entities);
        }

        public Builder<T> file(String excelFile) {
            files.add(excelFile);

            return this;
        }

        private void traitement() {
            //Analyse la classe source
            ModelMapper mapper = ModelMapper.getInstance();
            SheetModel sheetModel = mapper.to(tClass);
            /*
             * le fichier
             */
            for (String file : files) {
                log.info("Traitement: {}", file);
                try (Workbook workbook = Excel.read(file)) {
                    log.info("Workbook");
                    ExcelSheet sheetAnnotation = sheetModel.getSheetAnnotation();
                    List<CellModel> cellModels = sheetModel.getCellModels();
                    Sheet sheet = Excel.getSheetSelected(sheetAnnotation, workbook);
                    log.info("sheet");
                    log.info("row");
                    readRows(sheet.rowIterator(), tClass, cellModels, sheetAnnotation.rowNumber());

                } catch (ExcelException | IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        private void readRows(Iterator<Row> rows, Class<T> tClass, List<CellModel> cellModels, int rowNumber) {
            while (rows.hasNext()) {
                Row row = rows.next();
                T entity = rowToEntity(rowNumber, row, tClass, cellModels);
                if (Objects.nonNull(entity)) {
                    if (Objects.isNull(listEntities)) {
                        listEntities = new ArrayList<>();
                    }
                    listEntities.add(entity);
                }
            }

        }

        private T rowToEntity(int rowNumber, Row row, Class<T> tClass, List<CellModel> cellModels) {
            log.debug("current row: {}", row.getRowNum());
            if (row.getRowNum() > rowNumber) {
                log.debug("add new Entity with number row {}", row.getRowNum());
                RowConverter rowConverter = RowConverter.getInstance();
                return rowConverter.toClass(tClass,cellModels,row);
            }
            return null;
        }
    }
}
