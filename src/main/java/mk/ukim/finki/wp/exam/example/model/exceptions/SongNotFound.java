package mk.ukim.finki.wp.exam.example.model.exceptions;

public class SongNotFound extends RuntimeException{
    public SongNotFound() {
        super("Song Not Found");
    }
}

