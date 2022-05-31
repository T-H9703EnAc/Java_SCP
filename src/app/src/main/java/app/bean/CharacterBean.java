package app.bean;

import java.io.Serializable;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Data;

@Data
public class CharacterBean implements Serializable{
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "名前", required = true)
    String name;
    
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "HP", required = true)
    String h;
    
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "こうげき", required = true)
    String a;
    
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "ぼうぎょ", required = true)
    String b;
    
    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "とくこう", required = true)
    String c;
    
    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "とくぼう", required = true)
    String d;
    
    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "すばやさ", required = true)
    String s;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
        .append("名前", name)
        .append("HP",h)
        .append("こうげき", a)
        .append("ぼうぎょ", b)
        .append("とくこう", c)
        .append("とくぼう", d)
        .append("すばやさ", s)
        .toString();
    }
}
