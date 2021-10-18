package com.songPlayer.exceptions;

public class PlaylistNotAvailableException extends Exception{
    @Override
    public String toString(){
        return "Playlist Not Found";
    }
}