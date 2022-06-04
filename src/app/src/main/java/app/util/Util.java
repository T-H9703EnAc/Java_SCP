package app.util;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import app.bean.CharacterBean;

public class Util {
    /**
     * ステータスを取得する
     * @param doc
     * @return ステータス
     */
    public static CharacterBean getStatus(Document doc){
        Elements elements = doc.getElementsByClass("table layout_right");      
        String hpText = elements.first().getElementsByTag("tr").next().first().text();
        String atText = elements.first().getElementsByTag("tr").next().next().first().text();
        String blText = elements.first().getElementsByTag("tr").next().next().next().first().text();
        String ctText = elements.first().getElementsByTag("tr").next().next().next().next().first().text();
        String dfText = elements.first().getElementsByTag("tr").next().next().next().next().next().first().text();
        String spText = elements.first().getElementsByTag("tr").next().next().next().next().next().next().first().text();
        System.out.println(hpText);
        System.out.println(atText);
        System.out.println(blText);
        System.out.println(ctText);
        System.out.println(dfText);
        System.out.println(spText);
        CharacterBean bean = new CharacterBean(
            "",
            replaceRegex(replaceRegex(hpText,Const.regex1),Const.regex3).strip(),
            replaceRegex(replaceRegex(atText,Const.regex1),Const.regex3).strip(),
            replaceRegex(replaceRegex(blText,Const.regex1),Const.regex3).strip(),
            replaceRegex(replaceRegex(ctText,Const.regex1),Const.regex3).strip(),
            replaceRegex(replaceRegex(dfText,Const.regex1),Const.regex3).strip(),
            replaceRegex(replaceRegex(spText,Const.regex1),Const.regex3).strip()
        );
        System.out.println(bean);
        return bean;
    }

    /**
     * 名前を取得する
     * @param doc
     * @return 名前
     */
    public static String getName(Document doc){
        Elements elements = doc.getElementsByTag("h1");
        String name = elements.first().getElementsByTag("a").first().html();
        System.out.println(name);       
        return replaceRegex(name, Const.regex2);
    }
    /**
     * 特定の正規表現で置換する
     * @param strArray
     * @return
     */
    public static String[] replaceRegexArray(String[] strArray, String regex){
        String[] array = new String[strArray.length];
        int index = 0;
        for(String str : strArray){
            array[index] = str.replaceAll(regex, "");
            index++;
        }
        return array;
    }

    /**
     * 特定の正規表現で置換する
     * @param strArray
     * @return
     */
    public static String replaceRegex(String str, String regex){
        str = str.replaceAll(regex, "");
        return str;
    }
}
