package org.api.excel.parser;

import org.apache.commons.lang3.time.StopWatch;
import sample.Bench;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.FileUtil;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class ParseExcelBenchMarkIT {
    private static final long MEGABYTE = 1024L * 1024L;
    private static final Logger log = LoggerFactory.getLogger(ParseExcelBenchMarkIT.class);

    /**
     *
     * 1 000 lines
     * 13:52:59.560 [INFO ] - Used memory is bytes: 10942352
     * 13:52:59.560 [INFO ] - Used memory is megabytes: 10
     * 13:52:59.561 [INFO ] - Elapsed Time in second: 0 s
     *
     * <p>
     * 10 000 lines
     * 13:52:59.359 [INFO ] - Used memory is bytes: 14604448
     * 13:52:59.359 [INFO ] - Used memory is megabytes: 13
     * 13:52:59.359 [INFO ] - Elapsed Time in second: 1 s
     * <p>
     *
     * 100 000 lines
     * 13:52:57.585 [INFO ] - Used memory is bytes: 51023016
     * 13:52:57.585 [INFO ] - Used memory is megabytes: 48
     * 13:52:57.586 [INFO ] - Elapsed Time in second: 12 s
     *
     */

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
    @ParameterizedTest(name = "{1} Number of lines({0})")
    @CsvSource(value = {"convertcsv.1000.xlsx:1000", "convertcsv.10000.xlsx:10000", "convertcsv.100000.xlsx:100000"}, delimiter = ':')
    void toEntities_file(String file,String size){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Vérifier la sortie (then)
        String excelFile = FileUtil.getAbsolutePath(file);
        //Une action se produit (when)
        Optional<List<Bench>> list = ParseExcel.clazz(Bench.class)
                .file(excelFile)
                .build();
        //
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

        assertThat(list).isPresent()
                .containsInstanceOf(List.class);
        List<Bench> personnes = list.get();
        assertThat(personnes).isNotEmpty().hasSize(Integer.parseInt(size));
    }
}
