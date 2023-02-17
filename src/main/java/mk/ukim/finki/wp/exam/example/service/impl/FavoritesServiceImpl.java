package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Favorites;
import mk.ukim.finki.wp.exam.example.model.Song;
import mk.ukim.finki.wp.exam.example.model.User;
import mk.ukim.finki.wp.exam.example.model.exceptions.SongAlreadyInFavorites;
import mk.ukim.finki.wp.exam.example.model.exceptions.SongNotFound;
import mk.ukim.finki.wp.exam.example.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.exam.example.repository.FavoritesRepository;
import mk.ukim.finki.wp.exam.example.repository.SongRepository;
import mk.ukim.finki.wp.exam.example.repository.UserRepository;
import mk.ukim.finki.wp.exam.example.service.FavoritesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    private final FavoritesRepository favoritesRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public FavoritesServiceImpl(FavoritesRepository favoritesRepository, UserRepository userRepository, SongRepository songRepository) {
        this.favoritesRepository = favoritesRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public Favorites getFavoritesForUser(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.favoritesRepository
                .findByUser(user)
                .orElseGet(() -> {
                    Favorites favorites = new Favorites(user);
                    return this.favoritesRepository.save(favorites);
                });
    }

    @Override
    public List<Song> listAllSongsInFavorites(Long id) {
        return this.favoritesRepository.findById(id).get().getSongs();
    }

    @Override
    public void addSongToFavorites(String username, Long id) {
        Favorites favorites = this.getFavoritesForUser(username);
        Song song = this.songRepository.findById(id)
                .orElseThrow(() -> new SongNotFound());
        if(favorites.getSongs()
                .stream().filter(i -> i.getId().equals(id))
                .collect(Collectors.toList()).size() > 0)
            throw new SongAlreadyInFavorites(id, username);
        favorites.getSongs().add(song);
        this.favoritesRepository.save(favorites);

    }

    @Override
    public void deleteSong(String username, Long id)
    {
        Favorites favorites = this.getFavoritesForUser(username);
        Song song = this.songRepository.findById(id)
                .orElseThrow(() -> new SongNotFound());

        favorites.getSongs().remove(song);
        this.favoritesRepository.save(favorites);

    }
}
