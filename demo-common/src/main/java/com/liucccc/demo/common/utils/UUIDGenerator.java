package com.liucccc.demo.common.utils;

import java.util.UUID;

/**
 * UUIDGenerator
 * <br>
 *
 * @author liuchao
 * @created date 2020/2/18 17:44
 */
public class UUIDGenerator {
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
