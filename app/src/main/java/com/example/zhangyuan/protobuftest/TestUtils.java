package com.example.zhangyuan.protobuftest;

public class TestUtils {
    public static String[] sTestNames10;
    public static String[] sTestNames50;
    public static String[] sTestNames100;

    public static void initTest() {
        sTestNames10 = new String[10];
        for (int i =0 ;i<10;i++){
            sTestNames10[i] = "sTestNames10_"+i;
        }
        sTestNames50 = new String[50];
        for (int i =0 ;i<50;i++){
            sTestNames50[i] = "sTestNames50_"+i;
        }
        sTestNames100 = new String[100];
        for (int i =0 ;i<100;i++){
            sTestNames100[i] = "sTestNames100_"+i;
        }
    }
}
