package mk.ukim.finki.wp.exam.example.repository;

import mk.ukim.finki.wp.exam.example.model.Favorites;
import mk.ukim.finki.wp.exam.example.model.Song;
import mk.ukim.finki.wp.exam.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites,Long> {
    Optional<Favorites> findByUser(User user);
}
