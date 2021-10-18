package com.songPlayer.command;

import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import java.util.List;

import com.songPlayer.dto.PlaylistResponse;
import com.songPlayer.exceptions.NoSuchSongInPlaylistException;
import com.songPlayer.exceptions.NoSuchSongInPoolException;
import com.songPlayer.services.PlaylistService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("ModifyPlaylist_AddSongsTest")
@ExtendWith(MockitoExtension.class)
public class ModifyPlaylist_AddSongsTest {

    private PrintStream standard = System.out;
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Mock
    PlaylistService playlistService;

    @InjectMocks
    ModifyPlaylist_AddSongs modifyPlaylist_AddSongs;

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standard);
    }

    @Test
    @DisplayName("executeAddSongsTest")
    public void executeAddSongsTest() throws NoSuchSongInPoolException, NoSuchSongInPlaylistException {
        List<String> songIdList = new ArrayList<>();
        songIdList.add("1");
        songIdList.add("2");
        songIdList.add("3");
        String expectedOutput = "Playlist ID - 1\nPlaylist Name - Yashu_Playlist_1\nSong IDs - 1 2 3";
        

        PlaylistResponse playlistResponse = new PlaylistResponse(1, "Yashu_Playlist_1", songIdList);

        when(playlistService.modifyPlaylist_AddSongs(1, songIdList)).thenReturn(playlistResponse);
        

        List<String> tokens = new ArrayList<>();
        tokens.add("MODIFY-PLAYLIST ADD-SONG");
        tokens.add("1");
        tokens.addAll(songIdList);

        modifyPlaylist_AddSongs.execute(tokens);

        Assertions.assertEquals(expectedOutput, outputStream.toString().trim());

    }

    
}