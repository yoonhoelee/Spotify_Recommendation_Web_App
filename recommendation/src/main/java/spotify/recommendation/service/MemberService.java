package spotify.recommendation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import spotify.recommendation.SessionConst;
import spotify.recommendation.dto.MemberDto;
import spotify.recommendation.dto.SongDto;
import spotify.recommendation.entity.Member;
import spotify.recommendation.entity.Song;
import spotify.recommendation.login.argumentresolver.Login;
import spotify.recommendation.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void savePreference(@Login Member loginMember, int cluster){

        loginMember.setPreferredCluster(cluster);
        memberRepository.save(loginMember);
    }

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public void deactivate(@Login Member loginMember){
        memberRepository.delete(loginMember);
    }

    public void update(@Login Member loginMember, String updatedPw){
        loginMember.setPassword(updatedPw);
        memberRepository.save(loginMember);
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

    public MemberDto entityToDto(@Login Member loginMember){

        MemberDto dto = new MemberDto();
        dto.setAge(loginMember.getAge());
        dto.setEmail(loginMember.getEmail());
        dto.setName(loginMember.getName());
        return dto;

    }

}
