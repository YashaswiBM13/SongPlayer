package com.songPlayer.repositories;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.songPlayer.entities.Song;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SongRepositoryTest")
public class SongRepositoryTest {
    private SongRepository songRepository;

    @BeforeEach
    public void setUp(){
        Map<String, Song> songMap = new HashMap<>();
        songMap.put("11", new Song("11", "Something In the Way", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana")));
        songMap.put("12", new Song("12","Lithium","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana")));
        songMap.put("14", new Song("14","Stay Away","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana")));
        
        songRepository = new SongRepository(songMap);
    }

    @Test
    @DisplayName("getSongById_ShouldReturnSong_givenSongId")
    public void getSongById_ShouldReturnSong_givenSongId() throws NoSuchSongInPlaylistException {
        // Arrange
        Song expectedSong = new Song("14","Stay Away","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana"));

        // Act
        // try{
            Song actualSong = songRepository.getSongById("14");
            Assertions.assertEquals(expectedSong,actualSong);
        // }
        // catch(NoSuchSongInPlaylistException e){
        //     e.printStackTrace();
        // }
    }

    @Test
    @DisplayName("getSongByid_ShouldReturnNull")
    public void getSongByid_ShouldReturnNull()  {
        try{
            Song actualSong = songRepository.getSongById("15");
            Assertions.assertNull(actualSong);
        }
        catch(NoSuchSongInPlaylistException e){
            e.printStackTrace();
        }
    }
    
}