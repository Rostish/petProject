package ru.javawebinar.topjava;


public class Main {
    public static void main(String[] args) {
        String HOST1 = "https://jsonplaceholder.typicode.com/posts/1";
        int n = 5;
        String temp = HOST1.substring(0,HOST1.lastIndexOf("/")+1);
        HOST1 = temp + n;
        System.out.format(HOST1);
    }
}
