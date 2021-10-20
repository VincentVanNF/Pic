package com.example.Service;

public class IsDig {
    public static boolean IsDigit(String str){
        for(int i = 0 ; i < str.length() ; i++){
            if(!Character.isDigit(str.charAt(i))){
                return  false;
            }
        }
        return true;
    }


}
