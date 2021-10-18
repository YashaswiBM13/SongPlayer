package com.songPlayer.command;

import java.util.List;

import com.songPlayer.dto.PlaylistResponse;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;
import com.songPlayer.services.IPlaylistService;

public class ModifyPlaylist_DeleteSongs implements ICommand {

    private final IPlaylistService iPlaylistService;

    public ModifyPlaylist_DeleteSongs(IPlaylistService iPlaylistService){
        this.iPlaylistService = iPlaylistService;
    }

    @Override
    public void execute(List<String> tokens) {
        Integer playlistId = Integer.parseInt(tokens.get(1));
        List<String> songIdList = tokens.subList(2, tokens.size());
        try{
            PlaylistResponse playlistResponse = iPlaylistService.modifyPlaylist_RemoveSongs(playlistId, songIdList);
            System.out.println("Playlist ID - "+playlistResponse.getPlaylistId());
            System.out.println("Playlist Name - "+playlistResponse.getPlaylistname());
            System.out.print("Song IDs - ");
            playlistResponse.getSongIdListOfPlaylist().stream()
                            .forEach(id -> System.out.print(id+" "));
            System.out.println();
        }
        catch(NoSuchSongInPlaylistException e){
            System.out.println(e);
        }

    }
    
}