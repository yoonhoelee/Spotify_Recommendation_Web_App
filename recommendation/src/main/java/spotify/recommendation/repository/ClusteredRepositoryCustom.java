package spotify.recommendation.repository;

import org.apache.catalina.Cluster;
import org.springframework.data.repository.query.Param;
import spotify.recommendation.dto.ClusteredDto;
import spotify.recommendation.entity.Clustered;

import java.util.List;
import java.util.Optional;

public interface ClusteredRepositoryCustom {

    int findClusteredByNameAndArtist(@Param("songName") String songName, @Param("artist") String artist);
}
