package com.muskteer.tm.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapObjUtils {

    public static Object toBean(Map<String, ?> map, Class<?> cls)
            throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException {
        Field[] fields = checkFor(map, cls);
        map = decorate(map);
        Object object = cls.newInstance();
        if (fields == null) {
            return null;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if ((map.get(fieldName) == null))
                continue;
            Method method = build(cls, field);
            try {
                method.invoke(object,
                        new Object[]{map.get(fieldName)});
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("method:" + method + ",value:" + map.get(fieldName));
            }
        }

        return object;
    }

    private static Method build(Class<?> cls, Field field)
            throws NoSuchMethodException, SecurityException {
        String firstLetter = field.getName().substring(0, 1).toUpperCase();
        String setMethodName = "set" + firstLetter
                + field.getName().substring(1);
        Method setMethod = cls.getDeclaredMethod(setMethodName,
                new Class[]{field.getType()});
        setMethod.setAccessible(true);
        return setMethod;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, ?> decorate(Map<String, ?> map)
            throws InstantiationException, IllegalAccessException {
        Iterator<?> entrys = map.entrySet().iterator();
        HashMap<String, Object> copymap = new HashMap<String, Object>();
        String mos = null;
        while (entrys.hasNext()) {
            mos = ((Entry<String, ?>) entrys.next()).getKey();
            copymap.put(mos.replaceAll("_", ""), map.get(mos));
        }
        return copymap;
    }

    private static Field[] checkFor(Map<String, ?> map, Class<?> cls) {
        if (map == null || cls == null)
            return null;
        return cls.getDeclaredFields();
    }

}
