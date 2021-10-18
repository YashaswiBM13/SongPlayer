package com.songPlayer.command;

import java.util.List;

import com.songPlayer.dto.PlayResponse;
import com.songPlayer.exceptions.NoActivePlaylistException;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;
import com.songPlayer.services.IPlaylistService;

public class PlaySongBySongID implements ICommand {

    private final IPlaylistService iPlaylistService;

    public PlaySongBySongID(IPlaylistService iPlaylistService){
        this.iPlaylistService = iPlaylistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String songId = tokens.get(1);
        try{
            PlayResponse playResponse = iPlaylistService.playSongOnActivePlaylistBySongId(songId);
            System.out.println("Current Song Playing");
            System.out.println("Song - "+playResponse.getSongName());
            System.out.println("Album - "+playResponse.getAlbumName());
            System.out.print("Artists - ");
            playResponse.getAllArtistsOfTheSong().stream()
                            .forEach(artist -> System.out.print(artist+","));

            System.out.println();
        }
        catch(NoActivePlaylistException | NoSuchSongInPlaylistException e){
            System.out.println(e);
        }


    }
    
}