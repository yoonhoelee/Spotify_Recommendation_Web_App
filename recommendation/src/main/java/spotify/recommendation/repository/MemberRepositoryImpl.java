package spotify.recommendation.repository;

import lombok.RequiredArgsConstructor;
import spotify.recommendation.entity.Member;

import javax.persistence.EntityManager;
import java.util.*;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private final EntityManager em;

    @Override
    public Optional<Member> findMemberEmail(String email) {
        return findAll().stream()
                .filter(m->m.getEmail().equals(email))
                .findFirst();
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

}
