package app.services;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import app.bean.CharacterBean;
import app.interfaces.ServiceImpl;
import app.util.CustomMappingStrategy;
import app.util.Util;

public class NormalService implements ServiceImpl{
    

    @Override
    public void callService(String[] args) {
        String result = "失敗";
        String url = getUrl(args[1]);
        try (Writer writer = Files.newBufferedWriter(Paths.get("./csv/result.csv"))){
                Connection connection = Jsoup.newSession().url(url).timeout(10000);
                Document doc = connection.get();
                CharacterBean bean = Util.getStatus(doc);
                bean.setName(Util.getName(doc));

                System.out.println(bean);
                List<CharacterBean> beanList = new ArrayList<>();
                beanList.add(bean);            
                outputCSV(writer,beanList);
                          
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    private String getUrl(String num){
        String baseUrl = "https://yakkun.com/swsh/zukan/n";
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);
        sb.append(num);
        return sb.toString();
    }

    private void outputCSV(Writer writer, List<CharacterBean> beanList) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{
       
        CustomMappingStrategy<CharacterBean> mappingStrategy = new CustomMappingStrategy<>();
        mappingStrategy.setType(CharacterBean.class);

        StatefulBeanToCsv<CharacterBean> beanToCsv = new StatefulBeanToCsvBuilder<CharacterBean>(writer)
        .withSeparator(CSVWriter.DEFAULT_SEPARATOR)        
        .withMappingStrategy(mappingStrategy)
        .build();
        beanToCsv.write(beanList);       
    }
    
}
