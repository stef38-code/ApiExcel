package org.api.excel.parser;

import org.apache.commons.lang3.time.StopWatch;
import org.api.excel.sample.Bench;
import org.api.excel.sample.Personne;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.FileUtil;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class ParseExcelBenchMarkTest {
    private static final long MEGABYTE = 1024L * 1024L;
    private static final Logger log = LoggerFactory.getLogger(ParseExcelBenchMarkTest.class);

    /**
     * 13:52:59.560 [INFO ] - Used memory is bytes: 10942352
     * 13:52:59.560 [INFO ] - Used memory is megabytes: 10
     * 13:52:59.561 [INFO ] - Elapsed Time in second: 0 s
     */
    @Test
    void toEntities_1000() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("convertcsv.1000.xlsx");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.clazz(Bench.class)
                .file(excelFile)
                        .build();
        //Vérifier la sortie (then)
        stopWatch.stop();
        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        log.info("Used memory is bytes: {}",memory);
        log.info("Used memory is megabytes: {}", bytesToMegabytes(memory));

        log.info("Elapsed Time in second: {} s", TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));

        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<Personne> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(1000);
    }

    /**
     * 13:52:59.359 [INFO ] - Used memory is bytes: 14604448
     * 13:52:59.359 [INFO ] - Used memory is megabytes: 13
     * 13:52:59.359 [INFO ] - Elapsed Time in second: 1 s
     */
    @Test
    void toEntities_10000() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("convertcsv.10000.xlsx");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.clazz(Bench.class)
                .file(excelFile)
                .build();
        //Vérifier la sortie (then)
        stopWatch.stop();
        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        log.info("Used memory is bytes: {}",memory);
        log.info("Used memory is megabytes: {}", bytesToMegabytes(memory));

        log.info("Elapsed Time in second: {} s", TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));

        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<Personne> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(10000);
    }

    /**
     * 13:52:57.585 [INFO ] - Used memory is bytes: 51023016
     * 13:52:57.585 [INFO ] - Used memory is megabytes: 48
     * 13:52:57.586 [INFO ] - Elapsed Time in second: 12 s
     */
    @Test
    void toEntities_100000() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Conditions préalables (given)
        String excelFile = FileUtil.getAbsolutePath("convertcsv.100000.xlsx");
        //Une action se produit (when)
        Optional<List<Personne>> optional = ParseExcel.clazz(Bench.class)
                .file(excelFile)
                .build();
        //Vérifier la sortie (then)
        stopWatch.stop();
        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        log.info("Used memory is bytes: {}",memory);
        log.info("Used memory is megabytes: {}", bytesToMegabytes(memory));

        log.info("Elapsed Time in second: {} s", TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));

        assertThat(optional).isPresent()
                .containsInstanceOf(List.class);
        List<Personne> personnes = optional.get();
        assertThat(personnes).isNotEmpty().hasSize(100000);
    }
    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}
