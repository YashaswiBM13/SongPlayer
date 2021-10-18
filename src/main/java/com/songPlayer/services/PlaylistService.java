package com.songPlayer.services;

import java.util.ArrayList;
import java.util.List;


import com.songPlayer.dto.PlayResponse;
import com.songPlayer.dto.PlaylistResponse;
import com.songPlayer.entities.Playlist;
import com.songPlayer.entities.Song;
import com.songPlayer.entities.Status;
import com.songPlayer.exceptions.EmptyPlaylistException;
import com.songPlayer.exceptions.NoActivePlaylistException;
import com.songPlayer.exceptions.NoActiveSongException;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;
import com.songPlayer.exceptions.NoSuchSongInPoolException;
import com.songPlayer.exceptions.PlaylistNotAvailableException;
import com.songPlayer.repositories.IPlaylistRepository;
import com.songPlayer.repositories.ISongRepository;

public class PlaylistService implements IPlaylistService {

    private final ISongRepository iSongRepository;
    private final IPlaylistRepository iPlaylistRepository;

    public PlaylistService(ISongRepository iSongRepository, IPlaylistRepository iPlaylistRepository){
        this.iSongRepository = iSongRepository;
        this.iPlaylistRepository = iPlaylistRepository;
    }

    @Override
    public Integer createPlaylist(String playlistName, List<String> songIdList) throws NoSuchSongInPoolException, NoSuchSongInPlaylistException{
        // List<Song> songsFromPool = iSongRepository.getAllSongs();
        List<String> songIdsFromPool = iSongRepository.getAllSongIds();

        if(!(songIdsFromPool.containsAll(songIdList))){
            throw new NoSuchSongInPoolException();
        }
        List<Song> songList = getSongList(songIdList);
       
        Integer playlistId = iPlaylistRepository.savePlaylist(playlistName, songList);
        return playlistId;
    }

    @Override
    public void removePlaylist(Integer playlistId) throws PlaylistNotAvailableException{
        List<Playlist> availablePlaylist = iPlaylistRepository.getAllPlaylists();

        Playlist playlist = iPlaylistRepository.getPlaylistById(playlistId);

        if(!availablePlaylist.contains(playlist)){
            throw new PlaylistNotAvailableException();
        }
        iPlaylistRepository.removePlaylist(playlistId);

    }

    @Override
    public PlaylistResponse modifyPlaylist_AddSongs(Integer playlistId, List<String> songIdList) throws NoSuchSongInPoolException, NoSuchSongInPlaylistException {
        // List<Song> songsFromPool = iSongRepository.getAllSongs();
        List<String> songIdsFromPool  = iSongRepository.getAllSongIds();

        if(!(songIdsFromPool.containsAll(songIdList))){
            throw new NoSuchSongInPoolException();
        }

        List<Song> songList = getSongList(songIdList);
        return iPlaylistRepository.addSongsToPlaylist(playlistId, songList);

    }

    @Override
    public PlaylistResponse modifyPlaylist_RemoveSongs(Integer playlistId, List<String> songIdList) throws NoSuchSongInPlaylistException{
        
        Playlist playlist = iPlaylistRepository.getPlaylistById(playlistId);
        // List<Song> songsFromPlaylist = playlist.getAllSongsOfPlaylist();
        List<String> songIdsFromPlaylist = playlist.getAllSongIdsOfPlaylist();

        if(!(songIdsFromPlaylist.containsAll(songIdList))){
            throw new NoSuchSongInPlaylistException();
        }
        List<Song> songList =  getSongList(songIdList);
        
        return iPlaylistRepository.removeSongsFromPlaylist(playlistId, songList);
    }

    @Override
    public PlayResponse playResponseByPlaylistId(Integer playlistId) throws EmptyPlaylistException {
        Playlist playlist = iPlaylistRepository.getPlaylistById(playlistId);
        if(playlist.getAllSongsOfPlaylist().isEmpty()){
            throw new EmptyPlaylistException();
        }
        iPlaylistRepository.activatePlaylistById(playlistId);

        List<Song> songList = playlist.getAllSongsOfPlaylist();
        Song song = songList.get(0);
        iPlaylistRepository.activateSongById(playlistId, song.getId());
        

        return new PlayResponse(song.getName(), song.getAlbum(), song.getArtistList());
    }

    @Override
    public PlayResponse playSongOnActivePlaylistByDirection (String direction) throws NoActivePlaylistException, NoActiveSongException, NoSuchSongInPlaylistException{
        Playlist playlist = getActivePlaylist();
        Integer playlistId = playlist.getId();
        Song song = getActiveSong(playlistId, direction);

        iPlaylistRepository.activateSongById(playlistId, song.getId());
        return new PlayResponse(song.getName(), song.getAlbum(), song.getArtistList());
    }

    @Override
    public PlayResponse playSongOnActivePlaylistBySongId (String songId) throws NoSuchSongInPlaylistException, NoActivePlaylistException {
        Playlist playlist = getActivePlaylist();
        List<Song> songsIdList = playlist.getAllSongsOfPlaylist();

        Song song = songsIdList.stream()
                                .filter(s -> songId.equalsIgnoreCase(s.getId()))
                                .findAny()
                                .orElseThrow(NoSuchSongInPlaylistException::new);
        iPlaylistRepository.activateSongById(playlist.getId(), songId);
        return new PlayResponse(song.getName(), song.getAlbum(), song.getArtistList());
    }


    public Playlist getActivePlaylist() throws NoActivePlaylistException{
        List<Playlist> playlists = iPlaylistRepository.getAllPlaylists();
        return playlists.stream()
                        .filter(p -> p.getStatus().equals(Status.ACTIVE))
                        .findAny()
                        .orElseThrow(NoActivePlaylistException::new);
    }


    public List<Song> getSongList(List<String> songIdList) throws NoSuchSongInPlaylistException{
        List<Song> songList = new ArrayList<>();
        for(String id: songIdList){
            songList.add(iSongRepository.getSongById(id));
        }
        return songList;
    }

    public Song getActiveSong(Integer playlistId, String direction) throws NoActiveSongException{
        Playlist playlist = iPlaylistRepository.getPlaylistById(playlistId);
        List<Song> songsFromPlaylist = playlist.getAllSongsOfPlaylist();
        boolean foundActiveSong = false;
        Song song = new Song();
        int numOfSongs = songsFromPlaylist.size();
        for(int i=0; i < numOfSongs; i++){
            Song iSong = songsFromPlaylist.get(i);

            if(iSong.getStatus().equals(Status.ACTIVE)){
                foundActiveSong = true;
                if(direction.equalsIgnoreCase("Back")) {
                    if(i == 0) {
                        song = songsFromPlaylist.get(numOfSongs-1);
                    }
                    else{
                        song = songsFromPlaylist.get(i-1);
                    }
                }
                else{
                    if(i == numOfSongs-1){
                        song = songsFromPlaylist.get(0);
                    }
                    else{
                        song = songsFromPlaylist.get(i+1);
                    }
                }
                break;
            }
        }
        if(!foundActiveSong){
            throw new NoActiveSongException();
        }
        return song;

    }
    
}