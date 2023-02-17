package mk.ukim.finki.wp.exam.example.model.exceptions;

public class AlbumNotFound extends RuntimeException{
    public AlbumNotFound() {
        super("Album Not Found");
    }
}

