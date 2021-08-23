package spotify.recommendation.repository;

import lombok.RequiredArgsConstructor;
import spotify.recommendation.dto.ClusteredDto;
import spotify.recommendation.entity.Clustered;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClusteredRepositoryImpl implements ClusteredRepositoryCustom{

    private final EntityManager em;


    @Override
    public int findClusteredByNameAndArtist(String songName, String artist) {
        return em.createQuery("select c.cluster from Clustered c where c.songName = :songName and c.artist = :artist")
                .getFirstResult();
    }
}
