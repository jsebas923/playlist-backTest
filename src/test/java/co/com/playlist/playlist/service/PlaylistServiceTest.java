package co.com.playlist.playlist.service;

import co.com.playlist.playlist.controller.dto.PlaylistData;
import co.com.playlist.playlist.controller.dto.SongData;
import co.com.playlist.playlist.data.model.Playlist;
import co.com.playlist.playlist.data.model.Song;
import co.com.playlist.playlist.data.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PlaylistServiceTest {

    @InjectMocks
    private PlaylistService playlistService;

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private SongService songService;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPlaylistTest(){
        List<Playlist> playlists = buildPlaylists();
        when(playlistRepository.findAll()).thenReturn(playlists);
        List<PlaylistData> playlistDataList = playlistService.getPlaylist();
        assertNotNull(playlistDataList);

    }

    @Test
    void getPlaylistByListNameTest(){
        List<Playlist> playlists = buildPlaylists();
        when(playlistRepository.findByName(anyString())).thenReturn(playlists);
        List<PlaylistData> playlistDataList = playlistService.getPlaylistByListName("prueba");
        assertNotNull(playlistDataList);
    }

    @Test
    void createPlaylistTest(){
        PlaylistData playlistData = builPlaylistData();
        when(playlistRepository.save(any())).thenReturn(buildPlayList());
        when(songService.createSong(buildSongData())).thenReturn(buildSong());
        assertNotNull(playlistService.createPlaylist(playlistData));
    }

    @Test
    void deletePlaylistByListNameEmptyTest(){
         List<Playlist> playlists = new ArrayList<>();
         when(playlistRepository.findByName(anyString())).thenReturn(playlists);
         assertFalse(playlistService.deletePlaylistByListName("prueba"));
     }

    @Test
    void deletePlaylistByListNameTest(){
        List<Playlist> playlists = buildPlaylists();
        when(playlistRepository.findByName(anyString())).thenReturn(playlists);
        assertTrue(playlistService.deletePlaylistByListName("name"));


    }

    private PlaylistData builPlaylistData(){
        SongData songData = buildSongData();
        List<SongData> songDataList = new ArrayList<>();
        songDataList.add(songData);

        return PlaylistData.builder()
                .nombre("listaPrueba")
                .descripcion("descripcionPrueba")
                .canciones(songDataList).build();
    }

    private SongData buildSongData(){
        SongData songData = SongData.builder()
                .titulo("titulo")
                .album("album")
                .anno("anno")
                .artista("artista")
                .genero("genero")
                .build();

        return songData;
    }

    private List<Playlist> buildPlaylists(){
        Song song = buildSong();
        Playlist playlist = buildPlayList();
        List<Playlist> playlistList = new ArrayList<>();

        playlistList.add(buildPlayList());

        return playlistList;
    }

    private Playlist buildPlayList(){
        Playlist playlist = new Playlist();
        playlist.setName("name");
        playlist.setDescription("decripcion");

        Set<Song> songs = new HashSet<>();
        songs.add(buildSong());
        playlist.setSongs(songs);
        return playlist;
    }
    private Song buildSong(){
        Song song = new Song();
        song.setTitle("titulo");
        song.setGenre("genero");
        song.setAlbum("album");
        song.setArtist("artisya");
        song.setAnno("2020");
        return song;
    }
}
