package org.api.excel.parser;


import org.apache.commons.collections4.CollectionUtils;
import org.api.excel.mapping.ModelMapper;
import org.api.excel.model.SheetModel;
import org.api.excel.services.WorkbookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParseExcel<T> {

    private final List<T> entities;

    private ParseExcel(Builder<T> builder) {
        this.entities = builder.listEntities;
    }

    public static <T> Builder<T> clazz(Class<T> clazz) {
        return new Builder<T>().clazz(clazz);
    }

    private List<T> getEntities() {
        return this.entities;
    }

    public static final class Builder<T> {
        private final WorkbookService<T> workbookService = new WorkbookService<>();
        private List<T> listEntities;
        private Class<T> tClass;
        private final List<String> files;

        private Builder() {
            this.files = new ArrayList<>();
            this.listEntities = new ArrayList<>();
        }

        public Builder<T> clazz(Class<T> obj) {
            this.tClass = obj;
            return this;
        }

        public Optional<List<T>> build() {
            //Analyse la classe source
            ModelMapper mapper = ModelMapper.getInstance();
            SheetModel sheetModel = mapper.to(tClass);
            /*
             * les fichiers
             */
            for (String file : files) {
                workbookService.execute(sheetModel, file, listEntities, tClass);
            }
            List<T> entities = new ParseExcel<>(this).getEntities();
            if(CollectionUtils.isNotEmpty(entities)) {
                return Optional.ofNullable(entities);
            }
            return Optional.empty();
        }

        public Builder<T> file(String excelFile) {
            files.add(excelFile);

            return this;
        }
    }
}
