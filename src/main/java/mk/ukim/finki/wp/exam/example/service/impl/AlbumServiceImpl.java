package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Album;
import mk.ukim.finki.wp.exam.example.repository.AlbumRepository;
import mk.ukim.finki.wp.exam.example.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Optional<Album> findById(Long id) {
        return this.albumRepository.findById(id);
    }

    @Override
    public List<Album> listAllAlbums() {
        return this.albumRepository.findAll();
    }

    @Override
    public void save(Album album) {
        this.albumRepository.save(album);
    }
}
