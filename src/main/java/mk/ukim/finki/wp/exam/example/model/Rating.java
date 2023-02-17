package mk.ukim.finki.wp.exam.example.model;

import javax.persistence.*;

@Entity
public class Rating {

    public Rating() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float rating;

    public float getRating() {
        return rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
