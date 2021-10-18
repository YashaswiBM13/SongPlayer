package com.songPlayer.services;

import java.util.List;

import com.songPlayer.entities.Song;

public interface ISongListService {
    public List<Song> listSongsBasedOnGenre(String genre);
    public List<Song> listSongsBasedOnArtist(String artist);
    public List<Song> listSongsBasedOnAlbum(String album);

    public List<Song> showHistoryOfRecentlyPlayedSongs(int totalSongsToDisplay);

}