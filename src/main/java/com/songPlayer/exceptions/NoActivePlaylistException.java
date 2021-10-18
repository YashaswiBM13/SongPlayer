package com.songPlayer.exceptions;

public class NoActivePlaylistException extends Exception{
    @Override
    public String toString(){
        return "No Playlist is selected. Please select a Playlist";
    }
}