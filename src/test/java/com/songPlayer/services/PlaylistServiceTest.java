package com.songPlayer.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import com.songPlayer.dto.PlaylistResponse;
import com.songPlayer.entities.Playlist;
import com.songPlayer.entities.Song;
import com.songPlayer.exceptions.NoActivePlaylistException;
import com.songPlayer.exceptions.NoActiveSongException;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;
import com.songPlayer.exceptions.NoSuchSongInPoolException;
import com.songPlayer.repositories.PlaylistRepository;
import com.songPlayer.repositories.SongRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("PlaylistServiceTest")
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {

    @Mock
    PlaylistRepository playlistRepository;

    @Mock
    SongRepository songRepository;

    @InjectMocks
    PlaylistService playlistService;

    List<Playlist> playlistList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        playlistList.add(new Playlist(1, "Yashu_Playlist_1",
                Arrays.asList(
                        new Song("11", "Something In the Way", "Rock", "Nevermind", "Nirvana",
                                Arrays.asList("Nirvana")),
                        new Song("12", "Lithium", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana")),
                        new Song("14", "Stay Away", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana"))

                )));

        playlistList.add(new Playlist(2, "Old_Playlist_2",
                Arrays.asList(new Song("15", "Lounge Act", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana")),
                        new Song("16", "BLOOD.", "Hip-Hop", "DAMN.", "Kendrick Lamar", Arrays.asList("Kendrick Lamar")),
                        new Song("17", "GOD.", "Hip-Hop", "DAMN.", "Kendrick Lamar", Arrays.asList("Kendrick Lamar"))

                )));

    }

    @Test
    @DisplayName("getActivePlaylist_ShouldReturnActivePlaylist")
    public void getActivePlaylist_ShouldReturnActivePlaylist() throws NoActivePlaylistException {

        Playlist playlist = playlistList.get(0);
        playlist.activatePlaylist();

        when(playlistRepository.getAllPlaylists()).thenReturn(playlistList);

        Playlist actualPlaylist = playlistService.getActivePlaylist();
        Assertions.assertEquals(playlist, actualPlaylist);

    }

    @Test
    @DisplayName("getActivePlaylist_ShouldThrowNoActivePlaylistException")
    public void getActivePlaylist_ShouldThrowNoActivePlaylistException() {

        when(playlistRepository.getAllPlaylists()).thenReturn(playlistList);

        // try{
        // Playlist actualPlaylist = playlistService.getActivePlaylist();
        // Assertions.assertEquals(playlist, actualPlaylist);

        Assertions.assertThrows(NoActivePlaylistException.class, () -> playlistService.getActivePlaylist());
        // }
        // catch(NoActivePlaylistException e){
        // e.printStackTrace();
        // }

    }

    @Test
    @DisplayName("getActiveSong_ShouldReturnActiveSong")
    public void getActiveSong_ShouldReturnActiveSong() throws NoActiveSongException {

        String direction = "BACK";
        Integer playlistId = 2;

        Playlist playlist = playlistList.get(1);

        when(playlistRepository.getPlaylistById(playlistId)).thenReturn(playlist);

        Song song = playlist.getAllSongsOfPlaylist().stream().filter(s -> s.getId().equals("16")).findAny()
                .orElse(null);

        song.activateSong();

        Song expectedSong = new Song("15", "Lounge Act", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana"));

        Song actualSong = playlistService.getActiveSong(playlistId, direction);
        Assertions.assertEquals(expectedSong, actualSong);

    }

    @Test
    @DisplayName("modifyPlaylist_AddSongsTest")
    public void modifyPlaylist_AddSongsTest() throws NoSuchSongInPoolException, NoSuchSongInPlaylistException {
        List<Song> songList = new ArrayList<>(List.of(
            new Song("11", "Something In the Way", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana"))
                    , new Song("12","Lithium","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"))
                    , new Song("14","Stay Away","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"))
        ));

        List<String> songIdList = new ArrayList<>(List.of("11","12","14"));

        PlaylistResponse playlistResponse = new PlaylistResponse(1, "Yashu_Playlist_1", songIdList);
        Integer playlistId = 1;

        when(songRepository.getAllSongIds()).thenReturn(songIdList);
        // when(playlistRepository.addSongsToPlaylist(playlistId,songList)).thenReturn(playlistResponse);

        PlaylistResponse actualPlaylistResponse = playlistService.modifyPlaylist_AddSongs(playlistId, songIdList);

        Assertions.assertEquals(playlistResponse,actualPlaylistResponse);


        
    }


}