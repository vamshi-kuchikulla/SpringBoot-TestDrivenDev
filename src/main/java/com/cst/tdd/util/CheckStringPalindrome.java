package com.cst.tdd.util;

public class CheckStringPalindrome {
   public boolean isPalindrome(String str) {
        String reverse ="";
        for(int i = str.length()-1; i>=0;i--){
            reverse = reverse + str.charAt(i);
        }
        if(str.equals(reverse)){
            return true;
        }else {
            return false;
        }
    }

   public String checkEvenOrOdd(String s1){
       int i = Integer.parseInt(s1);
       if(i%2==0){
           return "even";
       }else{
           return "odd";
       }
   }
}
