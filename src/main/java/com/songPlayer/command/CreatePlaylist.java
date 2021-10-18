package com.songPlayer.command;

import java.util.List;


import com.songPlayer.exceptions.NoSuchSongInPlaylistException;
import com.songPlayer.exceptions.NoSuchSongInPoolException;
import com.songPlayer.services.IPlaylistService;

public class CreatePlaylist implements ICommand {

    private final IPlaylistService iPlaylistService;

    public CreatePlaylist(IPlaylistService iPlaylistService){
        this.iPlaylistService = iPlaylistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String playlistName = tokens.get(1);
        // List<String> songIdList = tokens.subList(2, tokens.size()-1);
        List<String> songIdList = tokens.subList(2, tokens.size());
        try{
            Integer playlistId = iPlaylistService.createPlaylist(playlistName, songIdList);
            System.out.println("Playlist ID - "+playlistId);
        }
        catch(NoSuchSongInPoolException | NoSuchSongInPlaylistException e){
            System.out.println(e);
        }
    };
    
}