package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.*;
import mk.ukim.finki.wp.exam.example.model.exceptions.*;
import mk.ukim.finki.wp.exam.example.repository.*;
import mk.ukim.finki.wp.exam.example.service.SongServce;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongServce {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final RatingRepository ratingRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository, ArtistRepository artistRepository, GenreRepository genreRepository, RatingRepository ratingRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Song> findAllSongs() {
        return this.songRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return this.songRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Song song=this.songRepository.findById(id).orElseThrow(SongNotFound::new);
        this.songRepository.delete(song);
    }

    @Override
    public void edit(Long id, String name, Long sec, String lyrics, Long artistId, Long albumId, List<Long> genreId, Long ratingId) {
        Song song=this.songRepository.findById(id).orElseThrow(SongNotFound::new);
        Album album=this.albumRepository.findById(albumId).orElseThrow(AlbumNotFound::new);
        Artist artist=this.artistRepository.findById(artistId).orElseThrow(ArtistNotFound::new);
        List<Genre> genre=this.genreRepository.findAllById(genreId);
        Rating rating=this.ratingRepository.findById(ratingId).orElseThrow(RatingNotFound::new);
        song.setName(name);
        song.setSec(sec);
        song.setLyrics(lyrics);
        song.setAlbum(album);
        song.setArtist(artist);
        song.setGenre(genre);
        song.setRating(rating);
        this.songRepository.save(song);
    }

    @Override
    public void save(String name, Long sec, String lyrics, Long artistId, Long albumId, List<Long> genreId, Long ratingId) {
        Album album=this.albumRepository.findById(albumId).orElseThrow(AlbumNotFound::new);
        Artist artist=this.artistRepository.findById(artistId).orElseThrow(ArtistNotFound::new);
        List<Genre> genre=this.genreRepository.findAllById(genreId);
        Rating rating=this.ratingRepository.findById(ratingId).orElseThrow(RatingNotFound::new);

        Song song=new Song(name,sec,lyrics,album,artist,genre,rating);
        this.songRepository.save(song);
    }

    @Override
    public Song like(Long id) {

        List<Song> songs = songRepository.findAll();
        Song song = songRepository.findById(id).orElseThrow(SongNotFound::new);
        for(Song s: songs)
        {

            if(s.getId().equals(id))
                s.setLikes(s.getLikes() + 1);

            songRepository.save(s);
        }
        return song;
    }

    @Override
    public List<Song> sortByNumberOfLikes(String order) {

        if(order.equals("Most Likes"))
        {
            return songRepository.findAllByOrderByLikesDesc();
        }
        else if(order.equals("Least Likes"))
        {
            return songRepository.findAllByOrderByLikesAsc();
        }
        else
        {
            return songRepository.findAll();
        }
    }

//    @Override
//    public List<Song> sortByNumberOfLikesByArtist(String order, Long artistId) {
//
//        if(order.equals("Most Likes"))
//        {
//            return songRepository.findSongsByArtistAndOrderByLikesDesc();
//        }
//        else if(order.equals("Least Likes"))
//        {
//            return songRepository.findSongsByArtistAndOrderByLikesAsc();
//        }
//        else
//        {
//            Artist artist=artistRepository.findById(artistId).orElseThrow(ArtistNotFound::new);
//            return artist.getSong();
//        }
//    }
}
