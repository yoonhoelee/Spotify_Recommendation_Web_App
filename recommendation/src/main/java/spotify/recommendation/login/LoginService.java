package spotify.recommendation.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spotify.recommendation.entity.Member;
import spotify.recommendation.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String email, String password){
        return memberRepository.findMemberEmail(email)
                .filter(m->m.getPassword().equals(password))
                .orElse(null);
    }
}
