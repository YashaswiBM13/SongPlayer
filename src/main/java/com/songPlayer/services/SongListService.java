package com.songPlayer.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.songPlayer.entities.Song;
import com.songPlayer.repositories.ISongRepository;

public class SongListService implements ISongListService {

    private final ISongRepository iSongRepository;
  

    public SongListService(ISongRepository iSongRepository){
        this.iSongRepository = iSongRepository;
    }

    @Override
    public List<Song> listSongsBasedOnGenre(String genre) {
        List<Song> allSongs = iSongRepository.getAllSongs();
        return allSongs.stream()
                .filter(song -> genre.equals(song.getGenre()))
                .collect(Collectors.toList());
                
    }

    @Override
    public List<Song> listSongsBasedOnArtist(String artist) {
        List<Song> allSongs = iSongRepository.getAllSongs();
        return allSongs.stream()
                .filter(song -> artist.equals(song.getAlbumArtist()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Song> listSongsBasedOnAlbum(String album) {
        List<Song> allSongs = iSongRepository.getAllSongs();
        return allSongs.stream()
                .filter(song -> album.equals(song.getAlbum()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Song> showHistoryOfRecentlyPlayedSongs(int totalSongsToDisplay) {
        List<Song> songsHistoryList = new ArrayList<>();
       
            songsHistoryList.addAll(iSongRepository.getAllSongs().stream()
            .limit(totalSongsToDisplay)
            .sorted((s1, s2) -> s1.getPlayCount().compareTo(s2.getPlayCount()))
            .collect(Collectors.toList()));

        return songsHistoryList;
    }
}
    
