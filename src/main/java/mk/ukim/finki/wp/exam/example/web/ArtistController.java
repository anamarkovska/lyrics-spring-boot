package mk.ukim.finki.wp.exam.example.web;

import mk.ukim.finki.wp.exam.example.model.*;
import mk.ukim.finki.wp.exam.example.model.exceptions.AlbumNotFound;
import mk.ukim.finki.wp.exam.example.model.exceptions.ArtistNotFound;
import mk.ukim.finki.wp.exam.example.model.exceptions.SongNotFound;
import mk.ukim.finki.wp.exam.example.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService service;
    private final SongServce songServce;
    private final AlbumService albumService;
    private final GenreService genreService;
    private final RatingService ratingService;

    public ArtistController(ArtistService service, SongServce songServce, AlbumService albumService, GenreService genreService, RatingService ratingService) {
        this.service = service;
        this.songServce = songServce;
        this.albumService = albumService;
        this.genreService = genreService;
        this.ratingService = ratingService;
    }


    @GetMapping
    public String showArtists(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Artist> artists = this.service.listAllArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("bodyContent", "list");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String showArtist(@PathVariable Long id, Model model) {

//        List<Song> songs;
//        Artist artist=this.service.findById(id).orElseThrow(ArtistNotFound::new);
//        if (order == null) {
//            songs=artist.getSong();
//        } else {
//            songs=songServce.sortByNumberOfLikesByArtist(order, id);
//        }

        Artist artist=this.service.findById(id).orElseThrow(ArtistNotFound::new);
        List<Song> songs = artist.getSong();
        model.addAttribute("artist", artist);
        model.addAttribute("songs", songs);
        List<Album>albums=artist.getAlbum();
        model.addAttribute("albums", albums);
        model.addAttribute("bodyContent", "artist");
        return "master-template";
    }

    @GetMapping("/album/{id}")
    public String showAlbums(@PathVariable Long id, Model model){
        Album album=this.albumService.findById(id).orElseThrow(AlbumNotFound::new);
        model.addAttribute("album",album);
        List<Song>songs=album.getSongs();
        model.addAttribute("songs",songs);
        Artist artist=album.getArtist();
        model.addAttribute("artist",artist);
        model.addAttribute("bodyContent", "album");
        return "master-template";
    }


    @GetMapping("/song/{id}")
    public String showSong(@PathVariable Long id, Model model){
        Song song=this.songServce.findById(id).orElseThrow(SongNotFound::new);
        model.addAttribute("song",song);
        String artist=song.getAlbum().getArtist().toString();
        model.addAttribute("artist",artist);
        model.addAttribute("bodyContent", "song");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.service.delete(id);
        return "redirect:/artists";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addArtistPage(Model model) {
        model.addAttribute("bodyContent", "add-form");
        return "master-template";
    }


    @GetMapping("/edit-form/artist/{id}")
    public String editArtistPage(@PathVariable Long id, Model model) {
        Artist artist=this.service.findById(id).orElseThrow(AlbumNotFound::new);
        model.addAttribute("artist",artist);
        model.addAttribute("bodyContent", "edit-form");
        return "master-template";
    }

    @DeleteMapping("/song/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        Song song=this.songServce.findById(id).orElseThrow(SongNotFound::new);
        Long artist=song.getArtist().getId();
        this.songServce.delete(id);
        return "redirect:/artists/"+artist;
    }

    @PostMapping("/addArtist")
    public String createArtist(@RequestParam String name,@RequestParam String url) {
        Artist artist=new Artist(name,url);
        this.service.save(artist);
        return "redirect:/artists";
    }

    @PostMapping("/editArtist/{id}")
    public String editArtist(@PathVariable Long id, @RequestParam String name,@RequestParam String url) {
        this.service.edit(id,name,url);
        return "redirect:/artists";
    }

    @GetMapping("/edit-form/song/{id}")
    public String editSongPage(@PathVariable Long id, Model model) {
            Song song = this.songServce.findById(id).orElseThrow(SongNotFound::new);
            Artist artist = song.getArtist();
            List<Artist> artists = this.service.listAllArtists();
            List<Album> albums = this.albumService.listAllAlbums();
            List<Genre> genres = this.genreService.listAllGenres();
            List<Rating> ratings = this.ratingService.listAllRatings();
            model.addAttribute("artists", artists);
            model.addAttribute("albums", albums);
            model.addAttribute("genres", genres);
            model.addAttribute("ratings", ratings);
            model.addAttribute("song", song);
            model.addAttribute("artist", artist);
        model.addAttribute("bodyContent", "edit-song");
        return "master-template";
    }


    @GetMapping("/add-form/song")
    public String getAddPageFromHome(Model model) {
            List<Artist> artists = this.service.listAllArtists();
            List<Album> albums = this.albumService.listAllAlbums();
            List<Genre> genres = this.genreService.listAllGenres();
            List<Rating> ratings = this.ratingService.listAllRatings();
            model.addAttribute("artists", artists);
            model.addAttribute("albums", albums);
            model.addAttribute("genres", genres);
            model.addAttribute("ratings", ratings);
        model.addAttribute("bodyContent", "add-song");
        return "master-template";
    }

    @PostMapping("/edit-song/{id}")
    public String editSong(@PathVariable(required = false) Long id,
                           @RequestParam String name,
                           @RequestParam Long sec,
                           @RequestParam String lyrics,
                           @RequestParam Long artistId,
                           @RequestParam Long albumId,
                           @RequestParam List<Long> genreId,
                           @RequestParam Long ratingId) {
        if (id != null) {
            this.songServce.edit(id,name,sec,lyrics,artistId,albumId,genreId,ratingId);
        } else {
            this.songServce.save(name,sec,lyrics,artistId,albumId,genreId,ratingId);
        }
        Song song=this.songServce.findById(id).orElseThrow(SongNotFound::new);
        Long artist=song.getArtist().getId();
        return "redirect:/artists/"+artist;
    }

    @PostMapping("/add-song")
    public String addSongFromHomePage(
                           @RequestParam String name,
                           @RequestParam Long sec,
                           @RequestParam String lyrics,
                           @RequestParam Long artistId,
                           @RequestParam Long albumId,
                           @RequestParam List<Long> genreId,
                           @RequestParam Long ratingId) {

        this.songServce.save(name,sec,lyrics,artistId,albumId,genreId,ratingId);
        return "redirect:/artists/"+artistId;
    }

    @GetMapping("/add-form/song/{id}")
    public String getAddPageFromArtist(@PathVariable Long id, Model model) {
        Artist artist=this.service.findById(id).orElseThrow(ArtistNotFound::new);
        List<Album> albums = this.albumService.listAllAlbums();
        List<Genre> genres = this.genreService.listAllGenres();
        List<Rating> ratings = this.ratingService.listAllRatings();
        model.addAttribute("artist", artist);
        model.addAttribute("albums", albums);
        model.addAttribute("genres", genres);
        model.addAttribute("ratings", ratings);
        model.addAttribute("bodyContent", "add-song-from-artist");
        return "master-template";
    }

    @PostMapping("/add-song/{id}")
    public String addSongFromArtist(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam Long sec,
            @RequestParam String lyrics,
            @RequestParam Long artistId,
            @RequestParam Long albumId,
            @RequestParam List<Long> genreId,
            @RequestParam Long ratingId) {

        this.songServce.save(name,sec,lyrics,artistId,albumId,genreId,ratingId);
        return "redirect:/artists/"+artistId;
    }

    @PostMapping("/{id}/like")
    public String like(@PathVariable Long id) {
        this.songServce.like(id);
        return "redirect:/songs";

    }



}
