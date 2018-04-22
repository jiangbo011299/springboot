package com.jiangbo.soket;

public class SingletonDemo2 {
    // 懒汉式
    private static SingletonDemo2 instance;

    private SingletonDemo2(){}

    public static synchronized SingletonDemo2 getInstance(){
        if (instance == null) {
            instance = new SingletonDemo2();
        }
        return instance;
    }
}
