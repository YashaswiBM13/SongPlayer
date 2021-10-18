package com.songPlayer.exceptions;

public class EmptyPlaylistException extends Exception{

    @Override
    public String toString(){
        return "Playlist is Empty";
    }
}