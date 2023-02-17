package mk.ukim.finki.wp.exam.example.model.exceptions;

public class RatingNotFound extends RuntimeException{
    public RatingNotFound() {
        super("Rating Not Found");
    }
}
