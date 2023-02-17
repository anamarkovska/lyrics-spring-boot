package mk.ukim.finki.wp.exam.example.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Where(clause = "deleted=false")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted=Boolean.FALSE;

    private String name;

    private String releaseYear;

    @ManyToOne//pojke albumi mozit da imat ist artist
    private Artist artist;

    @Column(length = 2000)
    private String url;

    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL)
    private List<Song> songs;

    public Album(String name, Artist artist, String url,String year) {
        this.name=name;
        this.artist=artist;
        this.url=url;
        this.releaseYear=year;
    }

    public Album(){}

    @Override
    public String toString() {
        return name;
    }

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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
