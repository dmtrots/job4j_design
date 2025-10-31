package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte b = 10;
        LOG.debug("b : {}", b);
        short s = 200;
        LOG.debug("s : {}", b);
        int i = 1000;
        LOG.debug("i : {}", b);
        long l = 10000L;
        LOG.debug("l : {}", b);
        float f = 3.14F;
        LOG.debug("f : {}", b);
        double d = 2.74562;
        LOG.debug("d : {}", b);
        char c = 'A';
        LOG.debug("c : {}", b);
        boolean bool = true;
        LOG.debug("bool : {}", b);
    }
}