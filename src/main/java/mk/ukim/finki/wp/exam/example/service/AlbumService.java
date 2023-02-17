package mk.ukim.finki.wp.exam.example.service;


import mk.ukim.finki.wp.exam.example.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Optional<Album> findById(Long id);
    List<Album> listAllAlbums();
    void save(Album album);
}
