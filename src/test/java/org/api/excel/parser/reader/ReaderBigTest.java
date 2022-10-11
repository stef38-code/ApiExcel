package org.api.excel.parser.reader;

import org.apache.commons.lang3.time.StopWatch;
import org.api.excel.core.utils.Info;
import org.api.excel.parser.ParseExcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sample.VuePM7SSAV;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("Ne pas Utiliser pour l'instant")
class ReaderBigTest {
    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    @BeforeEach
    void setUp() {
        String file = "first.xlsx";
        File file1 = new File(file);
        if (file1.exists()) {
            file1.delete();
        }
    }

    @Test
    void readFile() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String file = "C:\\Users\\betton\\Downloads\\GET_TEMPS_SAISIS_SOUMIS_APPROUVES_20220928.xlsx";
        Optional<List<VuePM7SSAV>> optional = ParseExcel.read(VuePM7SSAV.class)
                .file(file)
                .build();


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

        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<VuePM7SSAV> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(120162);
    }

}
