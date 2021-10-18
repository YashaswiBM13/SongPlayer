package com.songPlayer.command;

import java.util.List;

import com.songPlayer.dto.PlayResponse;
import com.songPlayer.exceptions.EmptyPlaylistException;
import com.songPlayer.services.IPlaylistService;

public class PlayPlaylist implements ICommand {

    private final IPlaylistService iPlaylistService;

    public PlayPlaylist(IPlaylistService iPlaylistService){
        this.iPlaylistService = iPlaylistService;
    }

    @Override
    public void execute(List<String> tokens) {
        Integer playlistId = Integer.parseInt(tokens.get(1));
        try{
            PlayResponse playResponse = iPlaylistService.playResponseByPlaylistId(playlistId);
            System.out.println("Current Song Playing");
            System.out.println("Song - "+playResponse.getSongName());
            System.out.println("Album - "+playResponse.getAlbumName());
            System.out.print("Artists - ");
            playResponse.getAllArtistsOfTheSong().stream()
                            .forEach(artist -> System.out.print(artist+","));

            System.out.println();
        }
        catch( EmptyPlaylistException e){
            System.out.println(e);
        }
    }
    
    
}