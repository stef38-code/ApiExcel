# APIExcel

## Description

Parser des fichiers XlSX ou XLS avec des annotations à l'aide de la bibliothèque Apache POI

### Annotations

| Annotation | Microsoft Excel |
|:----------:|:---------------:|
|    Book    |    WorkBook     |
|    Page    |      Sheet      |
|    Box     |      Cell       |

### Definition

#### Book

```java
import org.api.excel.annotations.Book;
import org.api.excel.annotations.Page;

@Book(value = {
        @Page(name = "Sheet1"),
        @Page(name = "Sheet2", rowNumber = 9),
        @Page(number = 2)
})
class Exemple {

}
```

#### Page

```java

@Page(name = "Sheet1")
public class Exemple {

}
```

#### Box

```java
public class Personne2 {
    @Box(stringFormat = true)
    private String name;
    @Box(number = 1)
    private String company;
    @Box(number = 2)
    private String address;
    @Box(number = 3, stringFormat = true)
    private String postalZip;
    @Box(number = 4)
    private String city;
    @Box(number = 5)
    private String guid;
// Add getter and setter
}
```

### Execution

#### 1 fichier

```java
class SimpleFile {
    public static void main(String[] args) {
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("exemple.xls");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.clazz(Personne.class)
                .file(excelFile)
                .build();
    }
}
```

#### n fichiers

```java
class MultiFile {
    public static void main(String[] args) {
        String fileXls = FileUtil.getAbsolutePath("exemple.xls");
        String fileXlsx = FileUtil.getAbsolutePath("exemple.xlsx");

        Optional<List<Personne>> optional = ParseExcel.clazz(Personne.class)
                .file(fileXls)
                .file(fileXlsx)
                .build();
    }
}
```

### Ressources

[java-builder-pattern-tricks](https://github.com/davidmoten/java-builder-pattern-tricks)
