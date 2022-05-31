package app.util;

import java.io.Writer;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CSVUtil<T> {  
    /**
     * 
     * @param writer
     * @param beanList
     * @throws CsvDataTypeMismatchException
     * @throws CsvRequiredFieldEmptyException
     */
    public void outputCSV(Writer writer, List<T> beanList, Class<T> bean) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{
       
        CustomMappingStrategy<T> mappingStrategy = new CustomMappingStrategy<>();
        mappingStrategy.setType(bean);

        StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
        .withSeparator(CSVWriter.DEFAULT_SEPARATOR)        
        .withMappingStrategy(mappingStrategy)
        .build();
        beanToCsv.write(beanList);       
    }
}
