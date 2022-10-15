package co.com.playlist.playlist.controller.restapi;

import co.com.playlist.playlist.controller.dto.PlaylistData;
import co.com.playlist.playlist.service.PlaylistService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    private final Log logger = LogFactory.getLog(PlaylistController.class);

    @GetMapping("/lists")
    public ResponseEntity<List<PlaylistData>> getPlaylist(){
        List<PlaylistData> playlistData = playlistService.getPlaylist();
        return new ResponseEntity<>(playlistData, HttpStatus.OK);
    }

    @GetMapping("/lists/{listName}")
    public ResponseEntity<List<PlaylistData>> getPlaylistByListName(@PathVariable("listName") String listName){
        List<PlaylistData> playlistData = playlistService.getPlaylistByListName(listName);
        if(!playlistData.isEmpty()){
            return new ResponseEntity<>(playlistData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/lists")
    public ResponseEntity<PlaylistData> createPlayList(@Valid @RequestBody PlaylistData playlistRequest){
        try{
            PlaylistData playlistData = playlistService.createPlaylist(playlistRequest);
            return new ResponseEntity<>(playlistData, HttpStatus.CREATED);
        }catch (Exception exception){
            logger.error(exception.getMessage(), exception);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/lists/{listName}")
    public ResponseEntity<HttpStatus> deletePlayList(@PathVariable("listName") String listName){
        try{
            boolean result = playlistService.deletePlaylistByListName(listName);
            return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND );
        }catch (Exception exception){
            logger.error(exception.getMessage(), exception);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
