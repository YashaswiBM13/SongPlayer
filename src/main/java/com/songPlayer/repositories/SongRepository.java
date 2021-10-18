package com.songPlayer.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.songPlayer.entities.Song;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;

public class SongRepository implements ISongRepository {

    private final Map<String, Song> songMap;

    public SongRepository(){
        songMap = new HashMap<>();
    }

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
    }

    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        songs.addAll(songMap.values());
        return songs;
    }

    @Override
    public Song getSongById(String songId) throws NoSuchSongInPlaylistException {
        return songMap.values().stream().filter(song -> songId.equalsIgnoreCase(song.getId())).findAny()
                .orElseThrow(NoSuchSongInPlaylistException::new);
    }

    @Override
    public void saveSong(Song song) {
        songMap.put(song.getId(), song);
    }

    @Override
    public List<String> getAllSongIds() {
        List<String> songIdList = new ArrayList<>();
        songIdList.addAll(songMap.keySet());
        return songIdList;
    } 
}