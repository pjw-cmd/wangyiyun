package com.zjgsu.utils;

import java.util.UUID;

public class IDGenerator {
    public static String generateID(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }
}
