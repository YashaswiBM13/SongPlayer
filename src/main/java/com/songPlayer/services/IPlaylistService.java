package com.songPlayer.services;

import java.util.List;

import com.songPlayer.dto.PlayResponse;
import com.songPlayer.dto.PlaylistResponse;

import com.songPlayer.exceptions.EmptyPlaylistException;
import com.songPlayer.exceptions.NoActivePlaylistException;
import com.songPlayer.exceptions.NoActiveSongException;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;
import com.songPlayer.exceptions.NoSuchSongInPoolException;
import com.songPlayer.exceptions.PlaylistNotAvailableException;

public interface IPlaylistService {
    public Integer createPlaylist(String playlistName, List<String> songIdList) throws NoSuchSongInPoolException, NoSuchSongInPlaylistException;
    public void removePlaylist(Integer playlistId) throws PlaylistNotAvailableException;

    public PlaylistResponse modifyPlaylist_AddSongs(Integer playlistId, List<String> songIdList) throws NoSuchSongInPoolException, NoSuchSongInPlaylistException;
    public PlaylistResponse modifyPlaylist_RemoveSongs(Integer playlistId, List<String> songIdList) throws NoSuchSongInPlaylistException;

    public PlayResponse playResponseByPlaylistId(Integer playlistId) throws EmptyPlaylistException;

    public PlayResponse playSongOnActivePlaylistByDirection(String direction) throws NoActivePlaylistException, NoActiveSongException, NoSuchSongInPlaylistException;
    public PlayResponse playSongOnActivePlaylistBySongId(String songId) throws NoSuchSongInPlaylistException, NoActivePlaylistException;

}