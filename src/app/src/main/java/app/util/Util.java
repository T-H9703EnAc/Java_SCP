package app.util;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Util {
    /**
     * ステータスを取得する
     * @param doc
     * @return ステータス
     */
    public static String[] getStatus(Document doc){
        /**翻訳したい言語のクラス要素 */
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
        String[] status = {hpText, atText, blText, ctText, dfText, spText};

        return replaceRegexArray(status, Const.regex1);
    }

    /**
     * 名前を取得する
     * @param doc
     * @return 名前
     */
    public static String getName(Document doc){
        /**翻訳したい言語のクラス要素 */
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
    public static String[] replaceRegexArray(String[] strArray, String regax){
        String[] array = new String[strArray.length];
        int index = 0;
        for(String str : strArray){
            array[index] = str.replaceAll(regax, "");
            index++;
        }
        return array;
    }

    /**
     * 特定の正規表現で置換する
     * @param strArray
     * @return
     */
    public static String replaceRegex(String str, String regax){
        str = str.replaceAll(regax, "");
        return str;
    }
}
