package spotify.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spotify.recommendation.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
