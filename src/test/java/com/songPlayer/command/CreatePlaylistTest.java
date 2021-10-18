package com.songPlayer.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import java.util.List;

import com.songPlayer.exceptions.NoSuchSongInPlaylistException;
import com.songPlayer.exceptions.NoSuchSongInPoolException;
import com.songPlayer.services.PlaylistService;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CreatePlaylistTest")
@ExtendWith(MockitoExtension.class)
public class CreatePlaylistTest {

    private PrintStream standard = System.out;
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Mock
    PlaylistService playlistService;

    @InjectMocks
    CreatePlaylist createPlaylist;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standard);
    }

    @Test
    @DisplayName("executeCreatePlaylist_ShouldReturnPlaylistId")
    public void executeCreatePlaylist_ShouldReturnPlaylistId()
            throws NoSuchSongInPoolException, NoSuchSongInPlaylistException {
        String expectedOutput = "Playlist ID - 1";

        String playlistName = "Yashu_Playlist_1";
        List<String> songIdList = new ArrayList<>();
        songIdList.add("1");
        songIdList.add("2");
        songIdList.add("3");

        when(playlistService.createPlaylist(playlistName, songIdList)).thenReturn(1);

        List<String> tokens = new ArrayList<>();
        tokens.add("CREATE-PLAYLIST");
        tokens.add(playlistName);
        tokens.addAll(songIdList);

        createPlaylist.execute(tokens);
        
        assertEquals(expectedOutput, outputStream.toString().trim());

    }
    
}