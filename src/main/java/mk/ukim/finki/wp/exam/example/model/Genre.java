package mk.ukim.finki.wp.exam.example.model;


import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Where(clause = "deleted=false")
public class Genre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean deleted=Boolean.FALSE;

    @ManyToMany(mappedBy = "genre")
    private List<Song> song;

    public Genre() {
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

    @Override
    public String toString() {
        return name;
    }

    public List<Song> getSong() {
        return song;
    }

    public void setSong(List<Song> song) {
        this.song = song;
    }
}
