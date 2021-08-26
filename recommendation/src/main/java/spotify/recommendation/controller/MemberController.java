package spotify.recommendation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spotify.recommendation.dto.MemberDto;
import spotify.recommendation.dto.SongDto;
import spotify.recommendation.entity.Member;
import spotify.recommendation.login.argumentresolver.Login;
import spotify.recommendation.service.MemberService;
import spotify.recommendation.service.SongService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SongService songService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Member member = new Member();
        member.setAge(form.getAge());
        member.setEmail(form.getEmail());
        member.setName(form.getName());
        member.setPassword(form.getPassword());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members/memberInfo")
    public String showMemberInfo(@Login Member loginMember, Model model){
        MemberDto memberDto = memberService.entityToDto(loginMember);
        model.addAttribute("memberDto", memberDto);
        return "members/memberInfo";
    }
}
