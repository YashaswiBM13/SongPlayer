package com.songPlayer.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.songPlayer.dto.PlaylistResponse;
import com.songPlayer.entities.Playlist;
import com.songPlayer.entities.Song;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PlaylistRepositoryTest")
public class PlaylistRepositoryTest {

    private PlaylistRepository playlistRepository;

    @BeforeEach
    public void setUp(){
        Map<Integer, Playlist> playlistmap = new HashMap<>();
        playlistmap.put(1, new Playlist(1, "Yashu_Playlist_1", Arrays.asList(
                new Song("11", "Something In the Way", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana"))
                , new Song("12","Lithium","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"))
                , new Song("14","Stay Away","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"))
        )));

        playlistmap.put(2, new Playlist(2, "Old_Playlist_2", Arrays.asList(
            new Song("15","Lounge Act","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"))
            ,new Song("16","BLOOD.","Hip-Hop","DAMN.","Kendrick Lamar",Arrays.asList("Kendrick Lamar"))
            , new Song("17","GOD.","Hip-Hop","DAMN.","Kendrick Lamar",Arrays.asList("Kendrick Lamar"))
        )));
        playlistRepository = new PlaylistRepository(playlistmap);
    }
    
    @Test
    @DisplayName("savePlaylist_shouldSavePlaylist")
    public void savePlaylist_shouldSavePlaylist(){
        List<Song> songList = Arrays.asList(
            new Song("18","PRIDE.","Hip-Hop","DAMN.","Kendrick Lamar",Arrays.asList("Kendrick Lamar"))
            , new Song("21","Flamenco Sketches","Jazz","Kind of Blue","Miles Davis",Arrays.asList("Miles Davis"))
        );

        Integer expectedPlaylistId = 1;
        Integer actualPlaylistId = playlistRepository.savePlaylist("SomeOtherPlaylist_1", songList);

        Assertions.assertEquals(expectedPlaylistId,actualPlaylistId);
    }

    @Test
    @DisplayName("addSongsToPlaylistTest")
    public void addSongsToPlaylistTest(){
        List<String> songIdList = new ArrayList<>(List.of("11","12","14"));
        PlaylistResponse expectedPlaylistResponse = new PlaylistResponse(1, "Yashu_Playlist_1", songIdList);

        Integer playlistId = 1;
        List<Song> songList = new ArrayList<>(List.of(
            new Song("15","Lounge Act","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"))
            ,new Song("16","BLOOD.","Hip-Hop","DAMN.","Kendrick Lamar",Arrays.asList("Kendrick Lamar"))
            , new Song("17","GOD.","Hip-Hop","DAMN.","Kendrick Lamar",Arrays.asList("Kendrick Lamar"))
        ));

        PlaylistResponse actualPlaylistResponse = playlistRepository.addSongsToPlaylist(playlistId, songList);

        Assertions.assertEquals(expectedPlaylistResponse, actualPlaylistResponse);
    }
}