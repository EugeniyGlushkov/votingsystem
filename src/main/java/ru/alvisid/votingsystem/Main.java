package ru.alvisid.votingsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alvisid.votingsystem.util.MenuUtils;
import ru.alvisid.votingsystem.util.UserUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

//del
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main (String[] args) throws Exception{

        BigInteger b = new BigInteger("2a", 16);
        System.out.println(b.toString(8));
        //10111

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {

                    //while (true) {
                        sleep(20000000);
                    //}
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
        System.out.println(thread.isInterrupted());
        thread.start();
        System.out.println(thread.isInterrupted());
        Thread.currentThread().sleep(1000);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        }
    }
