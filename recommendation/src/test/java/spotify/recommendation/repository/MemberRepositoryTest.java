package spotify.recommendation.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spotify.recommendation.entity.Member;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    void testMember(){
        Member member = new Member("ubrain0624@gmail.com", "hiThisIsAdmin", "Adam", 28, Sex.MALE);
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId()).get();
        assertThat(savedMember.getId()).isEqualTo(findMember.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember).isEqualTo(member);
    }
}