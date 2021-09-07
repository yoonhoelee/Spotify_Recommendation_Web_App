package spotify.recommendation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spotify.recommendation.dto.MemberDto;
import spotify.recommendation.entity.Member;
import spotify.recommendation.entity.Song;
import spotify.recommendation.form.MemberDeactivateForm;
import spotify.recommendation.form.MemberForm;
import spotify.recommendation.form.PasswordUpdateForm;
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
        List<Song> songListByMember = songService.findByMember(loginMember.getId());
        model.addAttribute("songListByMember", songListByMember);
        return "members/memberInfo";
    }

    @GetMapping("/members/deactivate")
    public String deactivateMemberForm(Model model){
        model.addAttribute("deactivateForm", new MemberDeactivateForm());
        return "members/deactivateMemberForm";
    }

    @PostMapping("/members/deactivate")
    public String deactivateMember(@Login Member loginMember, @Valid MemberDeactivateForm deactivateForm, BindingResult result){

        if (result.hasErrors()) {
            return "members/deactivateMemberForm";
        }

        String name = deactivateForm.getName();
        String email = deactivateForm.getEmail();
        String password = deactivateForm.getPassword();

        if(loginMember.getName().equals(name) && loginMember.getEmail().equals(email) && loginMember.getPassword().equals(password)){
            memberService.deactivate(loginMember);
            return "home";
        }
        else{
            return "members/deactivateMemberForm";
        }
    }

    @GetMapping("/members/update")
    public String memberUpdateForm(Model model){
        model.addAttribute("passwordUpdateForm", new PasswordUpdateForm());
        return "members/updateForm";
    }

    @PostMapping("/members/update")
    public String deactivateMember(@Login Member loginMember, @Valid PasswordUpdateForm passwordUpdateForm, BindingResult result){
        if (result.hasErrors()) {
            return "members/updateForm";
        }
        if(loginMember.getPassword().equals(passwordUpdateForm.getCurrentPw())){
            String updatedPassword = passwordUpdateForm.getUpdatedPw();
            memberService.update(loginMember, updatedPassword);
            return "members/passwordUpdated";
        }
        else{
            return "members/updateForm";
        }
    }
}
