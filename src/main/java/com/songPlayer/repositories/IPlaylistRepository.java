package com.songPlayer.repositories;
import java.util.List;

import com.songPlayer.dto.PlaylistResponse;
import com.songPlayer.entities.Playlist;
import com.songPlayer.entities.Song;


public interface IPlaylistRepository {
    public Integer savePlaylist(String playlistName, List<Song> songList);
    public void removePlaylist(Integer playlistId) ;
    public List<Playlist> getAllPlaylists();
    public Playlist getPlaylistById(Integer playlistId);

    public PlaylistResponse addSongsToPlaylist(Integer playlistId, List<Song> songList) ;
    public PlaylistResponse removeSongsFromPlaylist(Integer playlistId, List<Song> songList) ;
   

    public void activatePlaylistById(Integer playlistId);
    public void activateSongById(Integer playlistId,String songId);

}