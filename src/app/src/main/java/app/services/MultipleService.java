package app.services;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import app.bean.CharacterBean;
import app.interfaces.ServiceImpl;
import app.util.CSVUtil;
import app.util.Util;

public class MultipleService implements ServiceImpl{

    @Override
    public void callService(String[] args) {
        String result = "失敗";
        List<String> urlList = new ArrayList<>();
        for(int i=1; i<args.length; i++){
            urlList.add(getUrl(args[i]));
        }

        List<CharacterBean> beanList = new ArrayList<>();
        CharacterBean bean = new CharacterBean();
        try (Writer writer = Files.newBufferedWriter(Paths.get("./csv/result.csv"))){
            for(String url: urlList){
                Connection connection = Jsoup.newSession().url(url).timeout(10000);
                Document doc = connection.get();
                bean = Util.getStatus(doc);
                bean.setName(Util.getName(doc));
                System.out.println(bean);
                beanList.add(bean);
                result = "成功";
            }
            CSVUtil<CharacterBean> csvUtil = new CSVUtil<>();
            csvUtil.outputCSV(writer, beanList, CharacterBean.class);
                          
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
}
