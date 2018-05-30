package ru.alvisid.votingsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alvisid.votingsystem.util.MenuUtils;
import ru.alvisid.votingsystem.util.UserUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

//del
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        /*List<Integer> list = new ArrayList<>();

        for (int i = 0;i < 24;i++) {
            list.add(333);
        }

        System.out.println("   " + list.get(0));
        System.out.println("  " + list.get(1) + " " + list.get(2));
        for (int i = 3, end = list.size();i < end;) {
            System.out.println(" " + list.get(i++) + " " + list.get(i++) + " " + list.get(i++));
            System.out.println(list.get(i++) + " " + list.get(i++) + " " + list.get(i++) + " " + list.get(i++));
        }*/

        String time = "Время по МСК 18:20 Время в США: 04:53 Время в ОАЭ: 01:27";
        String[] times = time.split("\\s*[А-Яа-я\\s:]*\\s+");

        String mskTime = times[1];
        String usaTime = times[2];
        String oaeTime = times[3];

        System.out.println(mskTime);
        System.out.println(usaTime);
        System.out.println(oaeTime);

        //18:20
        //04:53
        //01:27
    }


}
