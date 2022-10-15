package co.com.playlist.playlist.controller.restapi;

import co.com.playlist.playlist.controller.dto.PlaylistData;
import co.com.playlist.playlist.controller.dto.SongData;
import co.com.playlist.playlist.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PlaylistControllerTest {

    @InjectMocks
    PlaylistController playlistController;

    @Mock
    private PlaylistService playlistService;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPlaylistTest(){
        PlaylistData playlistData = builPlaylistData();
        List<PlaylistData> playlistDataList = new ArrayList<>();
        playlistDataList.add(playlistData);
        when(playlistService.getPlaylist()).thenReturn(playlistDataList);

        ResponseEntity<List<PlaylistData>> response = playlistController.getPlaylist();
        assertEquals(playlistDataList.size(),1);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getPlaylistByListName200(){
        PlaylistData playlistData = builPlaylistData();
        List<PlaylistData> playlistDataList = new ArrayList<>();
        playlistDataList.add(playlistData);
        when(playlistService.getPlaylistByListName(anyString())).thenReturn(playlistDataList);
        ResponseEntity<List<PlaylistData>> response = playlistController.getPlaylistByListName(anyString());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getPlaylistByListName404NotFound(){
        List<PlaylistData> playlistDataList = new ArrayList<>();
        when(playlistService.getPlaylistByListName(anyString())).thenReturn(playlistDataList);
        ResponseEntity<List<PlaylistData>> response = playlistController.getPlaylistByListName(anyString());
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void createPlayList200(){
        PlaylistData playlistData = builPlaylistData();
        when(playlistService.createPlaylist(playlistData)).thenReturn(playlistData);
        ResponseEntity<PlaylistData> response = playlistController.createPlayList(playlistData);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void deletePlayList204(){
        when(playlistService.deletePlaylistByListName(anyString())).thenReturn(true);
        ResponseEntity<HttpStatus> response = playlistController.deletePlayList("lista1");
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void deletePlayList400(){
        when(playlistService.deletePlaylistByListName(anyString())).thenReturn(false);
        ResponseEntity<HttpStatus> response = playlistController.deletePlayList("lista1");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
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
}
