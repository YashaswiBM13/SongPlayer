package com.songPlayer.config;

import com.songPlayer.command.CommandInvoker;
import com.songPlayer.command.CreatePlaylist;
import com.songPlayer.command.DeletePlaylist;
import com.songPlayer.command.ICommand;
import com.songPlayer.command.ListSongBasedOnAlbum;
import com.songPlayer.command.ListSongBasedOnArtist;
import com.songPlayer.command.ListSongBasedOnGenre;
import com.songPlayer.command.ModifyPlaylist_AddSongs;
import com.songPlayer.command.ModifyPlaylist_DeleteSongs;
import com.songPlayer.command.PlayHistory;
import com.songPlayer.command.PlayPlaylist;
import com.songPlayer.command.PlaySongByDirection;
import com.songPlayer.command.PlaySongBySongID;
import com.songPlayer.repositories.IPlaylistRepository;
import com.songPlayer.repositories.ISongRepository;
import com.songPlayer.repositories.PlaylistRepository;
import com.songPlayer.repositories.SongRepository;
import com.songPlayer.repositories.data.DataLoader;

import com.songPlayer.repositories.data.SongData;
import com.songPlayer.services.IPlaylistService;
import com.songPlayer.services.ISongListService;
import com.songPlayer.services.PlaylistService;
import com.songPlayer.services.SongListService;

public class ApplicationConfig {

    private final ISongRepository iSongRepository = new SongRepository();
    private final IPlaylistRepository iPlaylistRepository = new PlaylistRepository();
    
    private final ISongListService iSongListService = new SongListService(iSongRepository);
    private final IPlaylistService iPlaylistService = new PlaylistService(iSongRepository, iPlaylistRepository);

    private final ICommand createPlaylist = new CreatePlaylist(iPlaylistService);
    private final ICommand deletePlaylist = new DeletePlaylist(iPlaylistService);
    private final ICommand modifyPlaylist_AddSongs = new ModifyPlaylist_AddSongs(iPlaylistService);
    private final ICommand modifyPlaylist_DeleteSongs = new ModifyPlaylist_DeleteSongs(iPlaylistService);
    private final ICommand playPlaylist = new PlayPlaylist(iPlaylistService);
    private final ICommand playSongByDirection = new PlaySongByDirection(iPlaylistService);
    private final ICommand playSongBySongId = new PlaySongBySongID(iPlaylistService);
    private final ICommand listSongBasedOnGenre = new ListSongBasedOnGenre(iSongListService);
    private final ICommand listSongBasedOnAlbum = new ListSongBasedOnAlbum(iSongListService);
    private final ICommand listSongBasedOnArtist = new ListSongBasedOnArtist(iSongListService);
    private final ICommand playHistory = new PlayHistory(iSongListService);

    private final CommandInvoker commandInvoker = new CommandInvoker();
    private final DataLoader dataLoader = new DataLoader();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.registerCommand("CREATE-PLAYLIST", createPlaylist);
        commandInvoker.registerCommand("DELETE-PLAYLIST", deletePlaylist);
        commandInvoker.registerCommand("MODIFY-PLAYLIST-ADD-SONG", modifyPlaylist_AddSongs);
        commandInvoker.registerCommand("MODIFY-PLAYLIST-DELETE-SONG", modifyPlaylist_DeleteSongs);
        commandInvoker.registerCommand("PLAY-PLAYLIST", playPlaylist);
        commandInvoker.registerCommand("PLAY-SONG", playSongByDirection);
        commandInvoker.registerCommand("PLAY-SONG-ID", playSongBySongId);
        commandInvoker.registerCommand("LIST-SONG-GENRE", listSongBasedOnGenre);
        commandInvoker.registerCommand("LIST-SONG-ARTIST", listSongBasedOnArtist);
        commandInvoker.registerCommand("LIST-SONG-ALBUM", listSongBasedOnAlbum);
        commandInvoker.registerCommand("HISTORY", playHistory);

        return commandInvoker;

    }

    public DataLoader getDataLoader(){
        dataLoader.register("SONG-DATA", new SongData(iSongRepository));
        return dataLoader;
    }

    
}