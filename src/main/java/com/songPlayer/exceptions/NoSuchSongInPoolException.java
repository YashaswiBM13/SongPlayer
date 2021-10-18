package com.songPlayer.exceptions;

public class NoSuchSongInPoolException extends Exception{

    @Override
    public String toString(){
        return "Some Requested Songs Not Available. Please try again.";
    }
    
}