package co.com.playlist.playlist.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "TBL_PLAYLIST")
public class Playlist {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
     cascade = {
            CascadeType.PERSIST,
             CascadeType.MERGE
     })
    @JoinTable(name = "playlist_song",
     joinColumns = { @JoinColumn(name = "playlist_id")} ,
     inverseJoinColumns = {@JoinColumn(name = "song_id")})
    private Set<Song> songs;
}
