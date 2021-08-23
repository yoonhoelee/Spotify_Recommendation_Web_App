package spotify.recommendation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spotify.recommendation.entity.Member;
import spotify.recommendation.login.argumentresolver.Login;
import spotify.recommendation.repository.ClusteredRepository;
import spotify.recommendation.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final ClusteredRepository clusteredRepository;

    public void savePreference(@Login Member loginMember, String songName, String artist){

        int cluster = clusteredRepository.findClusteredByNameAndArtist(songName, artist);
        loginMember.setPreferredCluster(cluster);
        memberRepository.save(loginMember);

    }

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public Optional<Member> findMemberEmail(String email) {
        return memberRepository.findAll().stream()
                .filter(m->m.getEmail().equals(email))
                .findFirst();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
