package com.songPlayer.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private final Integer id;
    private final String name;
    private final List<Song> songList;
    private Status status;

    

    public Playlist(Integer id, String name, List<Song> songList){
        this.id = id;
        this.name = name;
        this.songList = songList;
        status = Status.INACTIVE;
    }

    public Integer getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public List<String> getAllSongIdsOfPlaylist(){
        List<String> songIdList = new ArrayList<>();
        songList.stream()
                .forEach(song -> songIdList.add(song.getId()));

        return songIdList;

    }
    public List<Song> getAllSongsOfPlaylist(){
        return songList;
    }
    
    public void addSongs(List<Song> songList){
        this.songList.addAll(songList);
    }
    public void removeSongs(List<Song> listOfSongs){
        songList.removeAll(listOfSongs);
    }
    public void activatePlaylist(){
        status = Status.ACTIVE;
    }
    public void deActivatePlaylist(){
        status = Status.INACTIVE;
    }
    public Status getStatus(){
        return status;
    }
    
    @Override
    public boolean equals(Object o){
        if(this== o)return true;
        if(!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return getId().equals(playlist.getId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", songList='" +songList + 
                '}';
    }
    
}