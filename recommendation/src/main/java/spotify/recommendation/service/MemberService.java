package spotify.recommendation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import spotify.recommendation.SessionConst;
import spotify.recommendation.entity.Member;
import spotify.recommendation.login.argumentresolver.Login;
import spotify.recommendation.repository.ClusteredRepository;
import spotify.recommendation.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final ClusteredRepository clusteredRepository;

    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    Member loginMember = (Member) request.getAttribute(SessionConst.LOGIN_MEMBER);

    public void savePreference(int cluster){

        loginMember.setPreferredCluster(cluster);

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
