package com.songPlayer.repositories;

import java.util.List;


import com.songPlayer.entities.Song;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;


public interface ISongRepository {
    public List<Song> getAllSongs();
    public List<String> getAllSongIds();
    public Song getSongById(String songId) throws NoSuchSongInPlaylistException;
    public void saveSong(Song song);


    
   

    
    
}