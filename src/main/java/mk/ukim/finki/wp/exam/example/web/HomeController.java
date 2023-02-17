package mk.ukim.finki.wp.exam.example.web;

import mk.ukim.finki.wp.exam.example.model.*;
import mk.ukim.finki.wp.exam.example.model.exceptions.SongNotFound;
import mk.ukim.finki.wp.exam.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final ArtistService service;
    private final SongServce songServce;
    private final AlbumService albumService;
    private final GenreService genreService;
    private final RatingService ratingService;

    public HomeController(ArtistService service, SongServce songServce, AlbumService albumService, GenreService genreService, RatingService ratingService) {
        this.service = service;
        this.songServce = songServce;
        this.albumService = albumService;
        this.genreService = genreService;
        this.ratingService = ratingService;
    }

    @GetMapping
    public String getHomePage(Model model) {

        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }



//    @GetMapping("/add-form/song")
//    public String addSongPage(Model model) {
//        List<Artist> artists = this.service.listAllArtists();
//        List<Album> albums = this.albumService.listAllAlbums();
//        List<Genre> genres = this.genreService.listAllGenres();
//        List<Rating> ratings = this.ratingService.listAllRatings();
//        model.addAttribute("artists", artists);
//        model.addAttribute("albums", albums);
//        model.addAttribute("genres", genres);
//        model.addAttribute("ratings", ratings);
//        return "edit-song.html";
//    }
//
//    @PostMapping("/save-song/{id}")
//    public String saveSong(@PathVariable(required = false) Long id,
//                           @RequestParam String name,
//                           @RequestParam Long sec,
//                           @RequestParam String lyrics,
//                           @RequestParam Long artistId,
//                           @RequestParam Long albumId,
//                           @RequestParam List<Long> genreId,
//                           @RequestParam Long ratingId) {
//        if (id != null) {
//            this.songServce.edit(id,name,sec,lyrics,artistId,albumId,genreId,ratingId);
//        } else {
//            this.songServce.save(name,sec,lyrics,artistId,albumId,genreId,ratingId);
//        }
//        return "redirect:/home";
//    }

}