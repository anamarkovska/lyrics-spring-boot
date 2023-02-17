package mk.ukim.finki.wp.exam.example.model.exceptions;

public class ArtistNotFound extends RuntimeException{
    public ArtistNotFound() {
        super("Artist not found");
    }
}
