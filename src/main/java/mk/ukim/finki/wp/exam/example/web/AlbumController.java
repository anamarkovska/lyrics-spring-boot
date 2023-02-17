package mk.ukim.finki.wp.exam.example.web;


import mk.ukim.finki.wp.exam.example.model.*;
import mk.ukim.finki.wp.exam.example.model.exceptions.ArtistNotFound;
import mk.ukim.finki.wp.exam.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ArtistService service;
    private final SongServce songServce;
    private final AlbumService albumService;
    private final GenreService genreService;
    private final RatingService ratingService;

    public AlbumController(ArtistService service, SongServce songServce, AlbumService albumService, GenreService genreService, RatingService ratingService) {
        this.service = service;
        this.songServce = songServce;
        this.albumService = albumService;
        this.genreService = genreService;
        this.ratingService = ratingService;
    }


    @GetMapping
    public String showAlbums(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Album> albums = this.albumService.listAllAlbums();
        model.addAttribute("albums", albums);
        model.addAttribute("bodyContent", "list-album");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String getAddAlbumPage(Model model) {
        List<Album> albums = this.albumService.listAllAlbums();
        model.addAttribute("albums", albums);
        List<Artist> artists=this.service.listAllArtists();
        model.addAttribute("artists",artists);
        model.addAttribute("bodyContent", "add-album");
        return "master-template";
    }

    @PostMapping("/add-song")
    public String addSong(
            @RequestParam String name,
            @RequestParam Long artistId,
            @RequestParam String url,
            @RequestParam String year) {

        Artist artist=this.service.findById(artistId).orElseThrow(ArtistNotFound::new);
        Album album=new Album(name,artist,url,year);
        this.albumService.save(album);
        return "redirect:/albums";
    }
}