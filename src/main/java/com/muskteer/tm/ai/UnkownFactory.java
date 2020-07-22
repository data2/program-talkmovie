package com.muskteer.tm.ai;

import java.util.Random;

public class UnkownFactory {
    static Random random = new Random();
    public static String getDes(String keyword){
        if (keyword.contains("?") || keyword.contains("？")) {
            keyword.replace("你", "我");
            keyword.replace("吗", "了");
            keyword.replace("？", "！");
            keyword.replace("?", "！");
        }
        if (keyword.contains("！") || keyword.contains("!")){
            keyword.replace("我","你");
            keyword.replace("了","吗");
            keyword.replace("!","？");
            keyword.replace("！","？");
        }
        switch (random.nextInt(10)){
            case 1 : return UnknowSentence.one.name;
            case 2 : return UnknowSentence.two.name;
            case 3 : return UnknowSentence.three.name;
            case 4 : return UnknowSentence.four.name;
            case 5 : return UnknowSentence.five.name;
            case 6 : return UnknowSentence.six.name;
            case 7 : return UnknowSentence.seven.name;
            case 8 : return UnknowSentence.eight.name;
            case 9 : return UnknowSentence.nine.name;
            case 10 : return UnknowSentence.ten.name;
        }
        return null;
    }

}
