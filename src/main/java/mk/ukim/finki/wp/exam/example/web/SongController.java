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
@RequestMapping("/songs")
public class SongController {

    private final ArtistService service;
    private final SongServce songServce;
    private final AlbumService albumService;
    private final GenreService genreService;
    private final RatingService ratingService;

    public SongController(ArtistService service, SongServce songServce, AlbumService albumService, GenreService genreService, RatingService ratingService) {
        this.service = service;
        this.songServce = songServce;
        this.albumService = albumService;
        this.genreService = genreService;
        this.ratingService = ratingService;
    }

    @GetMapping
    public String showSongs(@RequestParam(required = false) String order, Model model) {

        List<Song> songs;
        if (order == null) {
            songs=this.songServce.findAllSongs();
        } else {
            songs=songServce.sortByNumberOfLikes(order);
        }

        model.addAttribute("songs", songs);
        model.addAttribute("bodyContent", "list-song");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        Song song=this.songServce.findById(id).orElseThrow(SongNotFound::new);
        Long artist=song.getArtist().getId();
        this.songServce.delete(id);
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
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
        model.addAttribute("bodyContent", "edit-songs-form");
        return "master-template";
    }

    @PostMapping("/edit-song/{id}")
    public String editSong(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam Long sec,
                           @RequestParam String lyrics,
                           @RequestParam Long artistId,
                           @RequestParam Long albumId,
                           @RequestParam List<Long> genreId,
                           @RequestParam Long ratingId) {
        this.songServce.edit(id,name,sec,lyrics,artistId,albumId,genreId,ratingId);
        return "redirect:/songs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
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

    @PostMapping("/add-song")
    public String addSong(
            @RequestParam String name,
            @RequestParam Long sec,
            @RequestParam String lyrics,
            @RequestParam Long artistId,
            @RequestParam Long albumId,
            @RequestParam List<Long> genreId,
            @RequestParam Long ratingId) {

        this.songServce.save(name,sec,lyrics,artistId,albumId,genreId,ratingId);
        return "redirect:/songs";
    }

    @PostMapping("/{id}/like")
    public String like(@PathVariable Long id) {
        this.songServce.like(id);
        return "redirect:/songs";

    }
}