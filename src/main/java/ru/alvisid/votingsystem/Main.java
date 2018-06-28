package ru.alvisid.votingsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alvisid.votingsystem.util.MenuUtils;
import ru.alvisid.votingsystem.util.UserUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

//del
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        float weight = 1; //вес кирпча
        int row = 5;      //номер ряда начиная с 1 с верху
        int num = 3;      //номер кирпича слева (или с права, как больше нравится) с 1
        System.out.println(pressueOnBrick(row, num, weight));
    }

    //давление на один кирпич
    public static float pressueOnBrick(int row, int num, float weight) {
        return pressue(row, num, weight) - weight;  //давление оказываемое на один кирпич = полное
                                                    //давление которое оказывает кирпич минус вес кирпича
    }

    //полное давление оказываемое кирпичём (включая свой вес)
    public static float pressue(int row, int num, float weight) {
        //в ряду не может быть кирпичей с номером меньше 1 и больше чем номер ряда(число кирпичей в ряду = номеру ряда)
        if (num < 1 || num > row) {
            return 0;
        }

        //давление которое оказывает кирпич равно сумме веса кирпича и половине веса кирпичей которые давят на него
        //слева и справа
        return weight + (pressue(row - 1, num - 1, weight) + pressue(row - 1, num, weight)) / 2;
    }
}
