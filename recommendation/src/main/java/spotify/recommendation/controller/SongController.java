package spotify.recommendation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spotify.recommendation.dto.SongDto;
import spotify.recommendation.entity.Member;
import spotify.recommendation.login.argumentresolver.Login;
import spotify.recommendation.service.MemberService;
import spotify.recommendation.service.SongService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final MemberService memberService;

    @GetMapping("/songs/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new SongForm());
        return "songs/createSongForm";
    }

    @PostMapping("/songs/new")
    public String createRecommendation(@Valid SongForm form, @Login Member loginMember, BindingResult result){
        if(result.hasErrors()){
            return "songs/createSongForm";
        }
        int cluster = songService.findCluster(form.getSongName(), form.getArtist());
        memberService.savePreference(loginMember, cluster);
        List<SongDto> clusteredDto = songService.findClusteredDto(loginMember);
        return "songs/recommendation";

    }

    @GetMapping("/songs/save")
    public String createSaveForm(Model model) {
        model.addAttribute("memberForm", new SongForm());
        return "songs/createSaveSongForm";
    }

    @PostMapping("/songs/save")
    public String saveSong(@Valid SongForm form,@Login Member loginMember, Model model){
        int cluster = loginMember.getPreferredCluster();
        songService.save(form.getSongName(), form.getArtist(), cluster, loginMember);

        return "redirect:/";
    }

}
