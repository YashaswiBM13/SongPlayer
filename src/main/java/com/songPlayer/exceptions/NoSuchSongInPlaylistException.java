package com.songPlayer.exceptions;

public class NoSuchSongInPlaylistException extends Exception{

    @Override
    public String toString(){
        return "Some Requested Songs are not present in the current playlist. Please try again";
    }
}