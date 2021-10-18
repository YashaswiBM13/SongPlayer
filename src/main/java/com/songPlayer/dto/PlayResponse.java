package com.songPlayer.dto;

import java.util.List;
import java.util.Objects;


public class PlayResponse {
    private String songName;
    private String albumName;
    private List<String> artists;
    

    public PlayResponse(String songName, String albumName, List<String> artists){
        this.songName = songName;
        this.albumName = albumName;
        this.artists = artists;
       
    }
    public String getSongName(){
        return songName;
    }
    public String getAlbumName(){
        return albumName;
    }
    public List<String> getAllArtistsOfTheSong(){
        return artists;
    }
    

    @Override
    public boolean equals(Object o){
        if(this== o) return true;
        if(!(o instanceof PlayResponse )) return false;
        PlayResponse playPlaylistResponse = (PlayResponse) o;
        return songName.equals(playPlaylistResponse.getSongName());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getSongName());
    }

    @Override
    public String toString() {
        return "PlayPlaylistResponse{" +
                "songName='" + songName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", artists='" +artists + 
                '}';
    }
}