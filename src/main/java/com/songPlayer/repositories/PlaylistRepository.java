package com.songPlayer.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.songPlayer.dto.PlaylistResponse;
import com.songPlayer.entities.Playlist;
import com.songPlayer.entities.Song;


public class PlaylistRepository implements IPlaylistRepository {

    private static Integer counter = 0;
    private final Map<Integer, Playlist> playlistmap;

    public PlaylistRepository(){
        playlistmap = new HashMap<>();
    }

    public PlaylistRepository(Map<Integer, Playlist> playlistmap){
        this.playlistmap = playlistmap;
    }

    @Override
    public Integer savePlaylist(String playlistName, List<Song> songList) {
        counter++;
        Playlist playlist = new Playlist(counter, playlistName, songList);
        Integer id = playlist.getId();
        playlistmap.put(id, playlist);
        return id;
    }

    @Override
    public void removePlaylist(Integer playlistId) {
        playlistmap.remove(playlistId);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        List<Playlist> allPlaylists = new ArrayList<>();
        allPlaylists.addAll(playlistmap.values());
        return allPlaylists;
    }

    @Override
    public Playlist getPlaylistById(Integer playlistId) {
        return playlistmap.get(playlistId);
    }

    @Override
    public PlaylistResponse addSongsToPlaylist(Integer playlistId, List<Song> songList) {
        Playlist playlist = playlistmap.get(playlistId);
        playlist.addSongs(songList);
        return new PlaylistResponse(playlistId, playlist.getName(), playlist.getAllSongIdsOfPlaylist());
    }

    @Override
    public PlaylistResponse removeSongsFromPlaylist(Integer playlistId, List<Song> songList) {
        Playlist playlist = playlistmap.get(playlistId);
        playlist.removeSongs(songList);

        return new PlaylistResponse(playlistId, playlist.getName(), playlist.getAllSongIdsOfPlaylist());
    }

    @Override
    public void activatePlaylistById(Integer playlistId) {      
        playlistmap.values().stream()
                        .forEach(playlist -> playlist.deActivatePlaylist());
        
        Playlist playlist = getPlaylistById(playlistId);
        playlist.activatePlaylist();
    }

    @Override
    public void activateSongById(Integer playlistId, String songId) {
        Playlist playlist = getPlaylistById(playlistId);
        List<Song> songs = playlist.getAllSongsOfPlaylist();      
        songs.stream().forEach(song -> song.deActivateSong());
        
       
        Song song = songs.stream()
                .filter(s -> songId.equalsIgnoreCase(s.getId()))
                .findAny()
                .orElse(null);

        song.activateSong();
        song.incrementPlayCount();
    }

   
}