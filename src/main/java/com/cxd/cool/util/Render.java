package com.cxd.cool.util;

import java.util.HashMap;
import java.util.Map;

public class Render {

    public static Map<String, Object> success() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "success");
        return map;
    }
}
