package spotify.recommendation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spotify.recommendation.entity.Member;
import spotify.recommendation.dto.MemberForm;
import spotify.recommendation.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("members/new")
    public String createForm(Model model){
        model.addAttribute("memberform", new MemberForm());
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
        member.setPassword(form.getPassword());
        member.setSex(form.getSex());

        memberService.join(member);
        return "redirect:/";
    }
}
