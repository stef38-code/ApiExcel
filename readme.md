# APIExcel

## Description

Parser des fichiers XlSX ou XLS avec des annotations à l'aide de la bibliothèque Apache POI

### Annotations

| Annotation | Microsoft Excel |
|:----------:|:---------------:|
|    [Book](src/main/java/org/api/excel/core/annotations/Book.java)    |    WorkBook     |
|    [Page](src/main/java/org/api/excel/core/annotations/Page.java)    |      Sheet      |
|    [Box](src/main/java/org/api/excel/core/annotations/Box.java)     |      Cell       |

### Definition

#### Book

```java
import org.api.excel.core.annotations.Book;
import org.api.excel.core.annotations.Page;

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
    @Box(name = "company")
    private String company;
    @Box(number = 2)
    private String address;
    @Box(number = 3, stringFormat = true, name ="postalZip")
    private String pZip;
    @Box(number = 4)
    private String city;
    @Box(number = 5)
    private String guid;
// Add getter and setter
}
```

### Execution

#### Reader 
````text
ParseExcel.clazz(<Class>)
                .file(<File xls or xlsx>)
                .build();
````

##### Un fichier

```java
class SimpleFile {
    public static void main(String[] args) {
        String excelFile = FileUtil.getAbsolutePath("personnes.xls");
        Optional<List<Personne>> optional = ParseExcel.clazz(Personne.class)
                .file(excelFile)
                .build();
    }
}
```

##### Plusieurs fichiers

```java
class MultiFile {
    public static void main(String[] args) {

        String fileXls = FileUtil.getAbsolutePath("personnes.xls");
        String fileXlsx = FileUtil.getAbsolutePath("personnes.xlsx");

        Optional<List<Personne>> optional = ParseExcel.clazz(Personne.class)
                .file(fileXls)
                .file(fileXlsx)
                .build();
    }
}
```
#### Tests
 
|                                    src TU                                     |                         Description                          |
|:-----------------------------------------------------------------------------:|:------------------------------------------------------------:|
|     [Empty file](src/test/java/org/api/excel/parser/ParseExcelTest.java)      |          Test sur des fichiers vide ou sans lignes           |
|        [sample ](src/test/java/org/api/excel/parser/PersonneTest.java)        |                Parse simple ou multi-fichiers                |
|     [Multi Sheet](src/test/java/org/api/excel/parser/MultiSheetTest.java)     |           Parse un fichier mais plusieurs onglets            |
|  [Bench Mark](src/test/java/org/api/excel/parser/ParseExcelBenchMarkIT.java)  | Parse 3 fichier 10000 lignes,100000 lignes et 1000000 lignes |

### Writer
Permet de convertir une liste d'entities en un fichier Excel.

`Il ne peut créer d'un seul onglet`

#### Exemple
[ref](src/test/java/org/api/excel/parser/write/WriteFileWithEntitiesTest.java)
* Entité
```java
@Page(name = "Feuil1")
public class Personne {

    @Box(name = "name")
    private String name;
    @Box(number = 1, name = "company")
    private String company;
    @Box(number = 2, name = "address")
    private String address;
    @Box(number = 3, name = "postalZip")
    private String postalZip;
    @Box(number = 4, name = "city")
    private String city;
    @Box(number = 5, name = "guid")
    private String guid;
    //setter and getter
}
```
* Exécution
```java
class main {
    public static void main(String[] args) {
        ParseExcel.write(Personne .class).
                file(file).
                entities(personnes).
                build();
    }
}
```


### Ressources

[java-builder-pattern-tricks](https://github.com/davidmoten/java-builder-pattern-tricks)
