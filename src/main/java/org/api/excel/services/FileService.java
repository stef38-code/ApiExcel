package org.api.excel.services;

import org.api.excel.mapping.ModelMapper;
import org.api.excel.model.SheetModel;
import org.api.excel.utils.Info;
import org.api.excel.utils.Return;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileService<T> {
    private final WorkbookService<T> workbookService = new WorkbookService<>();

    public Optional<List<T>> execute(Class<T> tClass, List<String> files) {
        //Analyse la classe source
        ModelMapper mapper = ModelMapper.getInstance();
        SheetModel sheetModel = mapper.to(tClass);
        List<T> values = new ArrayList<>();
    /*
     * les fichiers
     */
    for (String file : files) {
        Optional<List<T>> list = workbookService.execute(sheetModel, file, tClass);
        list.ifPresentOrElse(values::addAll,()-> Info.print(this,"No Data"));
    }
    return Return.byDefaultOptionalEmpty(values);
}

}
