package com.muskteer.tm.ai;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AiService {

    Random random = new Random();

    public String reply(String keyword){
        int option = random.nextInt(10);
        try{
            switch (option){
                case 1: UnkownFactory.getDes(keyword);
                case 3: UnkownFactory.getDes(keyword);
                case 7: UnkownFactory.getDes(keyword);
                case 9: UnkownFactory.getDes(keyword);
                default: return BaiduBaikeFactory.getContent(keyword);
            }
        }catch (Exception e){
            e.printStackTrace();
            return UnkownFactory.getDes(keyword);
        }
    }



}
