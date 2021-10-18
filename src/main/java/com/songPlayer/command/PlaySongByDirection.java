package com.songPlayer.command;

import java.util.List;

import com.songPlayer.dto.PlayResponse;
import com.songPlayer.exceptions.NoActivePlaylistException;
import com.songPlayer.exceptions.NoActiveSongException;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;
import com.songPlayer.services.IPlaylistService;

public class PlaySongByDirection implements ICommand {

    private final IPlaylistService iPlaylistService;

    public PlaySongByDirection(IPlaylistService iPlaylistService){
        this.iPlaylistService = iPlaylistService;
    }
    @Override
    public void execute(List<String> tokens) {
        String direction = tokens.get(1).toUpperCase();
        try{
            PlayResponse playResponse = iPlaylistService.playSongOnActivePlaylistByDirection(direction);
            System.out.println("Current Song Playing");
            System.out.println("Song - "+playResponse.getSongName());
            System.out.println("Album - "+playResponse.getAlbumName());
            System.out.print("Artists - ");
            playResponse.getAllArtistsOfTheSong().stream()
                            .forEach(artist -> System.out.print(artist+","));

            System.out.println();
        }
        catch(NoActivePlaylistException | NoActiveSongException | NoSuchSongInPlaylistException e){
            System.out.println(e);
        }

    }
    
}