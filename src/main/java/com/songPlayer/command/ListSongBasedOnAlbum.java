package com.songPlayer.command;

import java.util.List;

import com.songPlayer.entities.Song;
import com.songPlayer.services.ISongListService;

public class ListSongBasedOnAlbum implements ICommand {

    private final ISongListService iSongListService;

    public ListSongBasedOnAlbum(ISongListService iSongListService) {
        this.iSongListService = iSongListService;
    }
    @Override
    public void execute(List<String> tokens) {
        String album = tokens.get(1);
        System.out.println("List Songs Based On Album: \n");
        List<Song> songList = iSongListService.listSongsBasedOnAlbum(album);
        

        songList.stream()
            .forEach(song -> {
            System.out.println("Song ID - "+song.getId());
            System.out.println("Song Name - "+song.getName());
            System.out.println("Genre - "+song.getGenre());
            System.out.println("Album - "+song.getAlbum());
            System.out.println("Album Artist - "+song.getAlbumArtist());                    
            System.out.print("Artists - ");
            song.getArtistList().stream()
                    .forEach(artist -> System.out.print(artist+","));
                    System.out.println();
            });
            System.out.println();

    }
    
}