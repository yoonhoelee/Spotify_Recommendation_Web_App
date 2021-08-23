package spotify.recommendation.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spotify.recommendation.entity.Member;
import spotify.recommendation.repository.MemberRepository;
import spotify.recommendation.service.MemberService;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public Member login(String email, String password){
        return memberService.findMemberEmail(email)
                .filter(m->m.getPassword().equals(password))
                .orElse(null);
    }
}
