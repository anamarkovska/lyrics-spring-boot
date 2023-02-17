package mk.ukim.finki.wp.exam.example.web;

import mk.ukim.finki.wp.exam.example.model.Favorites;
import mk.ukim.finki.wp.exam.example.model.Song;
import mk.ukim.finki.wp.exam.example.model.User;
import mk.ukim.finki.wp.exam.example.model.exceptions.SongNotFound;
import mk.ukim.finki.wp.exam.example.service.FavoritesService;
import mk.ukim.finki.wp.exam.example.service.SongServce;
import org.springframework.stereotype.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;
    private final SongServce songServce;

    public FavoritesController(FavoritesService favoritesService, SongServce songServce) {
        this.favoritesService = favoritesService;
        this.songServce = songServce;
    }

    @GetMapping
    public String getFavoritesPage(HttpServletRequest req, Model model) {
        String username = req.getRemoteUser();
        Favorites favorites = this.favoritesService.getFavoritesForUser(username);
        model.addAttribute("songs", this.favoritesService.listAllSongsInFavorites(favorites.getId()));
        model.addAttribute("bodyContent", "favorites");
        return "master-template";
    }

    @PostMapping("/add-song/{id}")
    public String addSongToFavorites(@PathVariable Long id,Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        this.favoritesService.addSongToFavorites(user.getUsername(), id);
        return "redirect:/favorites";

    }

    @DeleteMapping("/delete-favorites/{songId}")
    public String removeSong(@PathVariable Long songId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        this.favoritesService.deleteSong(user.getUsername(),songId);
        return "redirect:/favorites";
    }
}
