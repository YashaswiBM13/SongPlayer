package com.songPlayer.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.songPlayer.entities.Song;
import com.songPlayer.repositories.SongRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("SongListServiceTest")
@ExtendWith(MockitoExtension.class)
public class SongListServiceTest {

    @Mock
    SongRepository songRepository;

    @InjectMocks
    SongListService songListService;

    List<Song> songList= new ArrayList<>();

    @BeforeEach
    public void setUp(){
        songList.add(new Song("11", "Something In the Way", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana")));
        songList.add(new Song("12","Lithium","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana")));
        songList.add(new Song("14","Stay Away","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana")));
        songList.add(new Song("15","Lounge Act","Rock","Nevermind","Nirvana",Arrays.asList("Nirvana")));
        songList.add(new Song("16","BLOOD.","Hip-Hop","DAMN.","Kendrick Lamar",Arrays.asList("Kendrick Lamar")));
        songList.add(new Song("17","GOD.","Hip-Hop","DAMN.","Kendrick Lamar",Arrays.asList("Kendrick Lamar")));
    }

    @Test
    @DisplayName("listSongsBasedOnArtist_shouldreturnArtistsList")
    public void listSongsBasedOnArtist_shouldreturnArtistsList(){

        List<Song> expectedSongList = new ArrayList<>();
        expectedSongList.add(songList.get(4));
        expectedSongList.add(songList.get(5));

        when(songRepository.getAllSongs()).thenReturn(songList);

        String artist = "Kendrick Lamar";
        List<Song> actualSongList = songListService.listSongsBasedOnArtist(artist);

        assertEquals(expectedSongList, actualSongList);
    }


    
}