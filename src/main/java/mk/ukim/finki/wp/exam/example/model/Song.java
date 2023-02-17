package mk.ukim.finki.wp.exam.example.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Where(clause = "deleted=false")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted=Boolean.FALSE;

    private String name;

    private Long sec; //vremetraenje na pesna vo sekundi

    private Integer likes = 0;

    @Column(length = 5000)
    private String lyrics;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Album album;

    @OneToOne
    private Rating rating;

    @ManyToMany
    private List<Genre> genre;

    public Song() {
    }


    public Song(String name, Long sec, String lyrics, Album album, Artist artist, List<Genre> genre, Rating rating){
        this.name = name;
        this.sec = sec;
        this.lyrics = lyrics;
        this.artist = artist;
        this.album = album;
        this.rating = rating;
        this.genre = genre;
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

    public Long getSec() {
        return sec;
    }

    public void setSec(Long sec) {
        this.sec = sec;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }


    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
