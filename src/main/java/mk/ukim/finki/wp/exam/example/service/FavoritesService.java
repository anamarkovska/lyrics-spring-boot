package mk.ukim.finki.wp.exam.example.service;

import mk.ukim.finki.wp.exam.example.model.Favorites;
import mk.ukim.finki.wp.exam.example.model.Song;

import java.util.List;

public interface FavoritesService {


    Favorites getFavoritesForUser(String username);

    List<Song> listAllSongsInFavorites(Long id);

    void addSongToFavorites(String username, Long id);

    void deleteSong(String username, Long id);
}
