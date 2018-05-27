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
        List<Integer> list = new ArrayList<>();

        for (int i = 0;i < 24;i++) {
            list.add(333);
        }

        System.out.println("   " + list.get(0));
        System.out.println("  " + list.get(1) + " " + list.get(2));
        for (int i = 3, end = list.size();i < end;) {
            System.out.println(" " + list.get(i++) + " " + list.get(i++) + " " + list.get(i++));
            System.out.println(list.get(i++) + " " + list.get(i++) + " " + list.get(i++) + " " + list.get(i++));
        }
    }
}
