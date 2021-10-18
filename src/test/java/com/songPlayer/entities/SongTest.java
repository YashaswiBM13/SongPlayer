package com.songPlayer.entities;



import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Song Test")
public class SongTest {

    @Test
    @DisplayName("IncrementPlayCountTest")
    public void incrementPlayCount_ShouldReturnIncrementedValue_givenCurrentPlayCount(){
        Song song = new Song("15", "Lounge Act", "Rock", "Nevermind", "Nirvana", Arrays.asList("Nirvana"));

        Integer expectedPlayCount = 1;
        song.incrementPlayCount();
        Integer actualPlayCount = song.getPlayCount();

        Assertions.assertEquals(expectedPlayCount, actualPlayCount);
    }
    
}