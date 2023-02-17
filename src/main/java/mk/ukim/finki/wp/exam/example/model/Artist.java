package mk.ukim.finki.wp.exam.example.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Where(clause = "deleted=false")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted=Boolean.FALSE;

    private String name;

    @Column(length = 2000)
    private String url;

    @OneToMany(mappedBy = "artist",cascade = CascadeType.ALL)
    private List<Song> song;

    @OneToMany(mappedBy = "artist",cascade = CascadeType.ALL)
    private List<Album> album;

    public Artist(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public Artist (){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSong() {
        return song;
    }

    public void setSong(List<Song> song) {
        this.song = song;
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
