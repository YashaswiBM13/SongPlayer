package com.songPlayer.repositories.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.songPlayer.entities.Song;

import com.songPlayer.repositories.ISongRepository;

public class SongData implements IData {

    private final ISongRepository iSongRepository;

    public SongData(ISongRepository iSongRepository) {
        this.iSongRepository = iSongRepository;
    }

    @Override
    public void load(String dataPath, String delimiter, String artistDelimiter) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(dataPath));
            String line = reader.readLine();
            while (line != null) {
                List<String> lineSplits = new ArrayList<>();
                lineSplits.addAll(Arrays.asList(line.split(artistDelimiter)));

                String original = lineSplits.get(0);
                String remainingLine = original.substring(0, original.lastIndexOf(delimiter));
                lineSplits.remove(0);

                
                List<String> tokens = Arrays.asList(remainingLine.split(delimiter));
                List<String> artistsList = new ArrayList<>();
                artistsList.add(original.substring(original.lastIndexOf(delimiter)+1));
                artistsList.addAll(lineSplits);

                addSong(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4), artistsList);

                line = reader.readLine();
            }
            reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
    public void addSong(String id, String name, String genre, String albumName, String albumArtist, List<String> artists){
        iSongRepository.saveSong(new Song(id, name, genre, albumName,albumArtist, artists));
    }
    
}