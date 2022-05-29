package app.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import app.interfaces.ServiceImpl;
import app.util.Util;

public class NormalService implements ServiceImpl{
    

    @Override
    public void callService(String[] args) {
        String result = "失敗";
        File file = new File("/Volumes/hdd/pg/Java/Java_SCP/test.html");
        String url = getUrl(args[1]);
        try (OutputStreamWriter osw  = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);){
                Connection connection = Jsoup.newSession().url(url).timeout(10000);
                Document doc = connection.get();
                bw.write(Util.getName(doc));
                bw.newLine();
                for(String status : Util.getStatus(doc)){
                    bw.write(status);
                    bw.newLine();
                }           
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
