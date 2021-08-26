package spotify.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spotify.recommendation.entity.Song;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByCluster(int cluster);

    @Query("select s.cluster from Song s where s.songName = :songName and s.artist = :artist")
    int findCluster(@Param("songName") String songName, @Param("artist") String artist);

}
