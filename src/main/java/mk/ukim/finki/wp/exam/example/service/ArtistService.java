package mk.ukim.finki.wp.exam.example.service;

import mk.ukim.finki.wp.exam.example.model.Artist;
import mk.ukim.finki.wp.exam.example.model.Song;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> listAllArtists();
    Optional<Artist> findById(Long id);
    void delete(Long id);
    void save(Artist artist);


    void edit(Long id, String name, String url);
}
