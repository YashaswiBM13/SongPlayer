package com.songPlayer.exceptions;

public class NoActiveSongException extends Exception {
    @Override
    public String toString(){
        return "No Song is selected. Please select a Song to play";
    }
}