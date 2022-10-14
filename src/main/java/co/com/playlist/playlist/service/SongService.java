package co.com.playlist.playlist.service;

import co.com.playlist.playlist.controller.dto.SongData;
import co.com.playlist.playlist.data.model.Song;
import co.com.playlist.playlist.data.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song createSong(SongData songRequest){
        Song song = new Song();
        song.setTitle(songRequest.getTitulo());
        song.setAlbum(songRequest.getAlbum());
        song.setAnno(songRequest.getAnno());
        song.setArtist(songRequest.getArtista());
        song.setGenre(songRequest.getGenero());

        return songRepository.save(song);

    }

    public SongData buildSongData(Song song){
        SongData songData = SongData.builder()
                .titulo(song.getTitle())
                .album(song.getAlbum())
                .anno(song.getAnno())
                .artista(song.getArtist())
                .genero(song.getGenre())
                .build();

        return songData;
    }
}
