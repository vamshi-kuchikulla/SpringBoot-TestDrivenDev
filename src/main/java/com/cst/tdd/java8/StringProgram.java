package com.cst.tdd.java8;

public class StringProgram {
    static String original = "krishna is a god";
    public static void main(String[] args) {
        String[] temp= original.split(" ");
        String reverse = "";
        for (int i=temp.length-1; i>=0;i--){
            reverse = reverse+ temp[i]+" ";
        }
        System.out.println(reverse.substring(0, reverse.length()-1));
    }
}
