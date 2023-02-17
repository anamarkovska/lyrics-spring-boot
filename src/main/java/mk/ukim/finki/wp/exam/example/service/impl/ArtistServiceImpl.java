package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Artist;
import mk.ukim.finki.wp.exam.example.model.Song;
import mk.ukim.finki.wp.exam.example.model.exceptions.ArtistNotFound;
import mk.ukim.finki.wp.exam.example.repository.ArtistRepository;
import mk.ukim.finki.wp.exam.example.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listAllArtists() {
        return this.artistRepository.findAll();
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return this.artistRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Artist artist=this.artistRepository.findById(id).orElseThrow(ArtistNotFound::new);
        this.artistRepository.delete(artist);
    }

    @Override
    public void save(Artist artist) {
        this.artistRepository.save(artist);
    }

    @Override
    public void edit(Long id, String name, String url) {
        Artist artist=this.artistRepository.findById(id).orElseThrow(ArtistNotFound::new);
        artist.setName(name);
        artist.setUrl(url);
        this.artistRepository.save(artist);
    }


}
