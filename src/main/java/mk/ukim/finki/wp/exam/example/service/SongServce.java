package mk.ukim.finki.wp.exam.example.service;

import mk.ukim.finki.wp.exam.example.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongServce {

    List<Song> findAllSongs();
    Optional<Song> findById(Long id);

    void delete(Long id);

    void edit(Long id, String name, Long sec, String lyrics, Long artistId, Long albumId, List<Long> genreId, Long ratingId);

    void save(String name, Long sec, String lyrics, Long artistId, Long albumId, List<Long> genreId, Long ratingId);

    Song like(Long id);

    List<Song> sortByNumberOfLikes(String order);

//    List<Song> sortByNumberOfLikesByArtist(String order, Long artistId);
}
