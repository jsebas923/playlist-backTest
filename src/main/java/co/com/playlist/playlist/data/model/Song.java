package co.com.playlist.playlist.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TBL_SONG")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Column(name = "album")
    private String album;

    @Column(name = "anno")
    private String anno;

    @Column(name = "genre")
    private String genre;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "songs")
    private Set<Playlist> playlists;


}
