package co.com.playlist.playlist.service;

import co.com.playlist.playlist.controller.dto.SongData;
import co.com.playlist.playlist.data.model.Song;
import co.com.playlist.playlist.data.repository.SongRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SongServiceTest {

    @InjectMocks
    private SongService songService;

    @Mock
    private SongRepository songRepository;


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSongTest(){
        SongData songData = SongData.builder()
                .titulo("titulo")
                .artista("artisya")
                .album("album")
                .anno("anno").genero("genero").build();

        Song song = new Song();
        song.setTitle("titulo");
        song.setGenre("genero");
        song.setArtist("artisya");
        song.setAnno("2020");

        when(songRepository.save(any())).thenReturn(song);
        Song songTemp = songService.createSong(songData);
        assertEquals(songTemp.getTitle(), songData.getTitulo());




    }

    @Test
    void buildSongData(){
        Song song = new Song();
        song.setTitle("titulo");
        song.setGenre("genero");
        song.setAlbum("album");
        song.setArtist("artisya");
        song.setAnno("2020");

        SongData songData = songService.buildSongData(song);
        assertNotNull(songData);

    }
}
