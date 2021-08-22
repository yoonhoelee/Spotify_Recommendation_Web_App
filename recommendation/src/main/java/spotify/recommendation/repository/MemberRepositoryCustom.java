package spotify.recommendation.repository;

import spotify.recommendation.entity.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findMemberEmail(String email);
}
