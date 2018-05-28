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

        System.out.println("love you");
        System.out.println("  <3  <3");
        for (int i = 4; i <= 5; i++) {
            for (int j = 5; j > i; j--) {
                System.out.printf(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.printf("<3");
            }
            System.out.println();
        }
        for (int i = 4; i >= 1; i--) {
            for (int j = 4; j >= i; j--) {
                System.out.printf(" ");
            }
            for (int j = i; j >= 1; j--) {
                System.out.printf("<3");
            }
            System.out.println();
        }
    }
}
