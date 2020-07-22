package com.muskteer.tm.ai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muskteer.tm.common.Idworker;
import com.muskteer.tm.common.bean.Film;
import com.muskteer.tm.dao.FilmDao;
import com.muskteer.tm.dao.impl.FilmDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ImportToMysql {
    @Autowired
    public FilmDao filmDao;
    public void insert() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("film.all")));
        String line;
        Film f = null;
        int i = 0;
        while ((line = br.readLine()) != null) {
            try {
                System.out.println(i++);
                f = JSONObject.parseObject(line, Film.class);
                f.setFilmNum(Idworker.nextId());
                if (!filmDao.inserFilm(f)){
                    System.out.println(JSON.toJSONString(f));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        br.close();
    }
}
