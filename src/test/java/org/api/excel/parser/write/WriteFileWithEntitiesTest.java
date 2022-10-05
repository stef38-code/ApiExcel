package org.api.excel.parser.write;

import org.apache.commons.lang3.time.StopWatch;
import org.api.excel.core.utils.Info;
import org.api.excel.parser.ParseExcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.Personne;
import tools.FileUtil;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class WriteFileWithEntitiesTest {
    private static final long MEGABYTE = 1024L * 1024L;
    private String file = "first.xlsx";
    private File file1;

    @BeforeEach
    void setUp() {
        file1 = new File(file);
        if (file1.exists()) {
            file1.delete();
        }
    }

    @Test
    void wirite_Lorsque_entities_Attend_unFichierExcel() {
        //Conditions préalables (given)

        List<Personne> personnes = getData();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Une action se produit (when)
        ParseExcel.write(Personne.class).file(file).entities(personnes).build();
        //Vérifier la sortie (then)
        stopWatch.stop();
        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        Info.print(this, "Used memory is bytes: {0}", memory);
        Info.print(this, "Used memory is megabytes: {0}", bytesToMegabytes(memory));
        Info.print(this, "Elapsed Time in second: {0} s", TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
        Info.print(this, "Elapsed Time in minutes: {0} s", TimeUnit.MILLISECONDS.toMinutes(stopWatch.getTime()));
        assertThat(file1).exists().isFile();
    }

    private List<Personne> getData() {
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("personnes.xlsx");
        //Une action se produit (when)
        return ParseExcel.read(Personne.class)
                .file(excelFile)
                .build().orElse(Collections.emptyList());
    }

    public long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}
