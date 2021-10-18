package com.songPlayer.entities;


import java.util.List;
import java.util.Objects;

public class Song {
    private String id;
    private String name;
    private String genre;
    // private Album album;
    private String album;
    private String albumArtist;
    private List<String> artists;
    private Status status;
    private Integer playCount = 0;
    
    public Song(){
        status = Status.INACTIVE;
    }
    public Song(String id, String name, String genre, String album,String albumArtist,List<String> artists){
        this.id = id;
        this.name = name;
        this.artists = artists;
        this.genre = genre;
        // this.album = album;
        this.album = album;
        this.albumArtist = albumArtist;
        status = Status.INACTIVE;
        
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getGenre(){
        return genre;
    }
    // public Album getAlbum(){
    //     return album;
    // }
    public String getAlbum(){
        return album;
    }
    public String getAlbumArtist(){
        return albumArtist;
    }
    public List<String> getArtistList(){
        return artists;
    }
    
    public void addArtist(String artist){
        artists.add(artist);
    }
    public void activateSong(){
        status = Status.ACTIVE;
    }
    public void deActivateSong(){
        status = Status.INACTIVE;
    }
    public Status getStatus(){
        return status;
    }
    
    public Integer getPlayCount() {
        return playCount;
    }
    public void incrementPlayCount(){
        playCount++;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Song)) return false;
        Song song = (Song) o;
        return getId().equals(song.getId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }
    
    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", album='" + album + '\'' +
                ", albumArtist='" + albumArtist + '\'' +
                ", artists='" + artists + '\'' +
                '}';
    }



}