package co.com.playlist.playlist.data.repository;

import co.com.playlist.playlist.data.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
}
