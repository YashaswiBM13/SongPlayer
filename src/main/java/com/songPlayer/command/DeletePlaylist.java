package com.songPlayer.command;

import java.util.List;

import com.songPlayer.exceptions.PlaylistNotAvailableException;
import com.songPlayer.services.IPlaylistService;

public class DeletePlaylist implements ICommand {

    private final IPlaylistService iPlaylistService;

    public DeletePlaylist(IPlaylistService iPlaylistService){
        this.iPlaylistService = iPlaylistService;
    }

    @Override
    public void execute(List<String> tokens) {
        Integer playlistId = Integer.parseInt(tokens.get(1));
        try{
            iPlaylistService.removePlaylist(playlistId);
            System.out.println("Delete Successful");
        }
        catch(PlaylistNotAvailableException e){
            System.out.println(e);
        }

    }
    
}