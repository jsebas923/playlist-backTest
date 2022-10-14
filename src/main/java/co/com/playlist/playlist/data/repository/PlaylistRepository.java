package co.com.playlist.playlist.data.repository;

import co.com.playlist.playlist.data.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    List<Playlist> findByName(String name);


}
