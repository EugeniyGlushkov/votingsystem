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
        float weight = 1; //��� ������
        int row = 5;      //����� ���� ������� � 1 � �����
        int num = 3;      //����� ������� ����� (��� � �����, ��� ������ ��������) � 1
        System.out.println(pressueOnBrick(row, num, weight));
    }

    //�������� �� ���� ������
    public static float pressueOnBrick(int row, int num, float weight) {
        return pressue(row, num, weight) - weight;  //�������� ����������� �� ���� ������ = ������
                                                    //�������� ������� ��������� ������ ����� ��� �������
    }

    //������ �������� ����������� �������� (������� ���� ���)
    public static float pressue(int row, int num, float weight) {
        //� ���� �� ����� ���� �������� � ������� ������ 1 � ������ ��� ����� ����(����� �������� � ���� = ������ ����)
        if (num < 1 || num > row) {
            return 0;
        }

        //�������� ������� ��������� ������ ����� ����� ���� ������� � �������� ���� �������� ������� ����� �� ����
        //����� � ������
        return weight + (pressue(row - 1, num - 1, weight) + pressue(row - 1, num, weight)) / 2;
    }
}
