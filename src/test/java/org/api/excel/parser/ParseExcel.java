package org.api.excel.parser;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ParseExcel<T> {
    private final List<T> entities;

    private ParseExcel(Builder builder) {
        this.entities = builder.listEntities;
    }
    public static <T> Builder clazz(Class<T> clazz){
        return new Builder<T>().clazz(clazz);
    }
    public static final class Builder<T>{
        private List<T> listEntities;
        private T instance;
        private Class<T> tClass;
        private List<String> files;
        private Builder(){
            this.files = new ArrayList<>();
        }
        public Builder<T> clazz(Class<T> obj){
            this.tClass = obj;
            return this;
        }
        public Optional<List<T>> build(){
            List entities = new ParseExcel(this).getEntities();
            return Optional.ofNullable(entities);
        }

        public Builder<T> file(String excelFile) {
            files.add(excelFile);
            return this;
        }
    }

    private  List<T> getEntities() {
        return this.entities;
    }
}
