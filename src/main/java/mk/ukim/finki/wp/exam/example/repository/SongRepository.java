package mk.ukim.finki.wp.exam.example.repository;

import mk.ukim.finki.wp.exam.example.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {

    List<Song> findAllByOrderByLikesAsc();

    List<Song> findAllByOrderByLikesDesc();

//    List<Song> findSongsByArtistAndOrderByLikesDesc();
//
//    List<Song> findSongsByArtistAndOrderByLikesAsc();
}
