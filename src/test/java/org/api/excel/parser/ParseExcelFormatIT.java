package org.api.excel.parser;

import org.apache.commons.lang3.time.StopWatch;
import org.api.excel.sample.Bench;
import org.api.excel.sample.FormatData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.FileUtil;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class ParseExcelFormatIT {
    private static final long MEGABYTE = 1024L * 1024L;
    private static final Logger log = LoggerFactory.getLogger(ParseExcelFormatIT.class);



    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
    @ParameterizedTest(name = "{1} Number of lines({0})")
    @CsvSource(value = {"format.xlsx:2"}, delimiter = ':')
    void toEntities_file(String file,String size){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Vérifier la sortie (then)
        String excelFile = FileUtil.getAbsolutePath(file);
        //Une action se produit (when)
        Optional<List<FormatData>> list = ParseExcel.clazz(FormatData.class)
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
        List<FormatData> personnes = list.get();
        assertThat(personnes).isNotEmpty().hasSize(Integer.parseInt(size));
    }
}
