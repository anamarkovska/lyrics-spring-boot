package mk.ukim.finki.wp.exam.example.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Where(clause = "deleted=false")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Boolean deleted=Boolean.FALSE;

    @ManyToMany
    private List<Song> songs;

    public Favorites(){}

    public Favorites(User user){
        this.user=user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
