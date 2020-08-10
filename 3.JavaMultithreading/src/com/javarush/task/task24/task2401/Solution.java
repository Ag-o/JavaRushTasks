package com.javarush.task.task24.task2401;

/* 
Создание своего интерфейса-маркера
*/
public class Solution {

    public static void main(String[] args) throws UnsupportedInterfaceMarkerException {
        SelfInterfaceMarker marker = new SelfInterfaceMarkerImpl();
        Util.testClass(marker);
    }
}
