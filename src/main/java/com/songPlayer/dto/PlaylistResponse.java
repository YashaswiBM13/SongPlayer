package com.songPlayer.dto;


import java.util.List;
import java.util.Objects;

public class PlaylistResponse {
    private Integer id;
    private String name;
    private List<String> songIdList;

    public PlaylistResponse(Integer id, String name, List<String> songIdList){
        this.id = id;
        this.name = name;
        this.songIdList = songIdList;
    }
    public Integer getPlaylistId(){
        return id;
    }
    public String getPlaylistname(){
        return name;
    }
    public List<String> getSongIdListOfPlaylist(){
        return songIdList;
    }
    @Override
    public boolean equals(Object o){
        if(this== o) return true;
        if(!(o instanceof PlaylistResponse )) return false;
        PlaylistResponse playlistResponse = (PlaylistResponse) o;
        return id.equals(playlistResponse.getPlaylistId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getPlaylistId());
    }

    @Override
    public String toString() {
        return "PlaylistResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", songIdList='" +songIdList + 
                '}';
    }

    
}