package spotify.recommendation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spotify.recommendation.dto.ClusteredForm;
import spotify.recommendation.dto.MemberForm;
import spotify.recommendation.entity.Member;
import spotify.recommendation.service.ClusteredService;
import spotify.recommendation.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ClusteredController {

    private final ClusteredService clusteredService;
    private final MemberService memberService;


    @GetMapping("/clustered/new")
    public String createForm(Model model){
        model.addAttribute("clusteredform", new ClusteredForm());
        return "clustered/createClusteredForm";
    }

    @PostMapping("/clustered/new")
    public String create(@Valid ClusteredForm form, BindingResult result){

        if (result.hasErrors()) {
            return "clustered/createClusteredForm";
        }
        int cluster = clusteredService.findCluster(form.getSongName(), form.getArtist());
        memberService.savePreference(cluster);
        clusteredService.findClusteredDto();
        return "clustered/recommendation";
    }

    @GetMapping("/clustered/save")
    public String createSaveForm(Model model){
        model.addAttribute("clusteredform", new ClusteredForm());
        return "clustered/createClusteredSaveForm";
    }
}
