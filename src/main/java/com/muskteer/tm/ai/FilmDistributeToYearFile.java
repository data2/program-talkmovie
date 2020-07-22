package com.muskteer.tm.ai;

import com.alibaba.fastjson.JSON;
import com.muskteer.tm.common.bean.Film;
import com.muskteer.tm.common.util.StringUtil;
import org.apache.commons.lang.math.NumberUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmDistributeToYearFile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("film.2018-2000")));
        Set<String> set = new HashSet<>();
        String line = null;
        Film f ;
        while((line = br.readLine()) != null){
            f = JSON.parseObject(line,Film.class);
            if (f != null && StringUtil.isNotEmpty(f.getYear()))
                set.add(f.getYear().trim());
        }
        br.close();
        System.out.println(set);
        System.out.println(set.size());



        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                new File("film.what"),true)));
        String line1 = null;
        Film f1 = null;
        for (String str : set){
            try{
                System.out.println(str + "开始");
                if (!NumberUtils.isNumber(str))
                    continue;
                br = new BufferedReader(new FileReader(new File("film.2018-2000")));
                BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                            new File("film." + str),true)));
//
//
                while((line1 = br.readLine()) != null){
                    try{
                        f1 = JSON.parseObject(line1,Film.class);
                        if(f1 != null && (StringUtil.isEmpty(f1.getYear()) || !NumberUtils.isNumber(f1.getYear()))){
                            bw.write(JSON.toJSONString(f1));
                            bw.newLine();
                        }
                        if (f1 != null && StringUtil.isNotEmpty(f1.getYear()) && f1.getYear().trim().equals(str)){
                            bw1.write(JSON.toJSONString(f1));
                            bw1.newLine();
                        }

                    }catch (Exception e){

                    }
                }
//
                br.close();
                bw1.flush();
                bw1.close();

                System.out.println(str + "end");
//
            }catch (Exception e){
                e.printStackTrace();

            }
        }
        bw.flush();
        bw.close();

    }
}
