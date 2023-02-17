package mk.ukim.finki.wp.exam.example.model.exceptions;

public class GenreNotFound extends RuntimeException{
    public GenreNotFound() {
        super("Genre Not Found");
    }
}
