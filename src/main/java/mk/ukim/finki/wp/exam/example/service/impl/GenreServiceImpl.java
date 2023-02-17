package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Genre;
import mk.ukim.finki.wp.exam.example.repository.GenreRepository;
import mk.ukim.finki.wp.exam.example.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    final private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> listAllGenres() {
        return this.genreRepository.findAll();
    }
}
