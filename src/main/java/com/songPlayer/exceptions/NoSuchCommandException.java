package com.songPlayer.exceptions;

public class NoSuchCommandException extends Exception{
    @Override
    public String toString(){
        return "Invalid Command!! Please specify right command";
    }
}