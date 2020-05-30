package com.zjgsu.utils;

import java.util.UUID;

public class IDGenerator {
    public static String generateID(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
