package mk.ukim.finki.wp.exam.example.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class SongAlreadyInFavorites extends RuntimeException{

    public SongAlreadyInFavorites(Long id, String username) {
        super(String.format("Song with id: %d already exists in favorites for user with username %s", id, username));
    }
}