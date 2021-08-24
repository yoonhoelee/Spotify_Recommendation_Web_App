package spotify.recommendation.repository;

import org.apache.catalina.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spotify.recommendation.entity.Clustered;

import java.util.List;

public interface ClusteredRepository extends JpaRepository<Clustered, Long>, ClusteredRepositoryCustom {

    List<Clustered> findByCluster(@Param("cluster") int cluster);
    List<Clustered> findByMember_id(@Param("member_id") int member_id);

}
