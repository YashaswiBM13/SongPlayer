package com.songPlayer.entities;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PlaylistTest")
public class PlaylistTest {
    
    @Test
    @DisplayName("getAllSongIdsOfPlaylist_SuccessTest")
    public void getAllSongIdsOfPlaylist_ShouldReturnListOfSongIds(){
        // Arrange
        Playlist playlist = new Playlist(1, "MY_PLAYLIST_1", Arrays.asList(
                new Song("11", "Something In the Way", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana"))
                , new Song("12","Lithium","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"))
                , new Song("13","Come as You Are","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"))
                , new Song("14","Stay Away","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"))
        ));

        List<String> expectedSongIds = Arrays.asList("11","12","13","14");

        // Act
        List<String> actualSongIds = playlist.getAllSongIdsOfPlaylist();

        // Assert
        Assertions.assertEquals(expectedSongIds, actualSongIds);
    }

}