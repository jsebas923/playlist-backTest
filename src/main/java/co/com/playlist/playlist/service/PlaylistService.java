package co.com.playlist.playlist.service;

import co.com.playlist.playlist.controller.dto.PlaylistData;
import co.com.playlist.playlist.controller.dto.SongData;
import co.com.playlist.playlist.data.model.Playlist;
import co.com.playlist.playlist.data.model.Song;
import co.com.playlist.playlist.data.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongService songService;

    /**
     * Method to return all playlists.
     * @return
     */
    public List<PlaylistData> getPlaylist(){

        List<Playlist> playlists = playlistRepository.findAll();
        List<PlaylistData> playlistData = new ArrayList<>();

        for (Playlist playlist: playlists){
            playlistData.add(buildPlaylistData(playlist));
        }
        return playlistData;
    }

    public List<PlaylistData> getPlaylistByListName(String listName){
       List<Playlist> playlists = playlistRepository.findByName(listName);

        List<PlaylistData> playlistData = new ArrayList<>();

        for (Playlist playlist: playlists){
            playlistData.add(buildPlaylistData(playlist));
        }
       return playlistData;
    }

    public PlaylistData createPlaylist(PlaylistData playlistRequest){
        Playlist playlist = buildPlayList(playlistRequest);
        PlaylistData playlistData = buildPlaylistData(
                playlistRepository.save(playlist)
        );
        return playlistData;
    }

    public Boolean deletePlaylistByListName(String listName){
        List<Playlist> playlists = playlistRepository.findByName(listName);
        if(!playlists.isEmpty()){
            for (Playlist playlist: playlists) {
                playlistRepository.deleteById(playlist.getId());
            }
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    private PlaylistData buildPlaylistData(Playlist playlist){

        List<SongData> songDataList = new ArrayList<>();
        for (Song song : playlist.getSongs()){
            SongData songData = songService.buildSongData(song);
            songDataList.add(songData);
        }
        PlaylistData playlistData = PlaylistData.builder()
                .nombre(playlist.getName())
                .descripcion(playlist.getDescription())
                .canciones(songDataList)
                .build();
        return playlistData;
    }

    private Playlist buildPlayList(PlaylistData playlistRequest){
        Playlist playlist = new Playlist();
        playlist.setName(playlistRequest.getNombre());
        playlist.setDescription(playlistRequest.getDescripcion());
        //List<Song> songList = new ArrayList<>();
        Set<Song> songSet = new HashSet<>();
        for (SongData songRequest: playlistRequest.getCanciones()){
            Song song = songService.createSong(songRequest);
            songSet.add(song);
        }
        playlist.setSongs(songSet);
        return playlist;

    }

}
