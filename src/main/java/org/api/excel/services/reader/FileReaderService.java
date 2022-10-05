package org.api.excel.services.reader;

import org.api.excel.mapping.ModelMapper;
import org.api.excel.model.commun.BookModel;
import org.api.excel.core.utils.Info;
import org.api.excel.core.utils.Return;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileReaderService<T> {
    private final WorkbookReaderService<T> workbookReaderServiceByPoi;

    public FileReaderService(WorkbookReaderService<T> workbookReaderService) {
        this.workbookReaderServiceByPoi = workbookReaderService;
    }

    public Optional<List<T>> execute(Class<T> tClass, List<String> files) {
        //Analyse la classe source
        ModelMapper mapper = ModelMapper.getInstance();
        BookModel bookModel = mapper.to(tClass);
        List<T> values = new ArrayList<>();
        /*
         * les fichiers
         */
        for (String file : files) {
            Optional<List<T>> list = workbookReaderServiceByPoi.execute(bookModel, file, tClass);
            list.ifPresentOrElse(values::addAll, () -> Info.print(this, "No Data for file {0}", file));
        }
        return Return.byDefaultOptionalEmpty(values);
    }

}
