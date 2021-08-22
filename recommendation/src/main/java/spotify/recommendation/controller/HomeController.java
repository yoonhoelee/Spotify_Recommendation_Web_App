package spotify.recommendation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spotify.recommendation.login.argumentresolver.Login;
import spotify.recommendation.entity.Member;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    @GetMapping("/")
    public String homeLoginArgumentResolver(@Login Member loginMember, Model model) {
//세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }
//세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
