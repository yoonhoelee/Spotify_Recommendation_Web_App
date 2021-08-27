package spotify.recommendation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spotify.recommendation.dto.SongDto;
import spotify.recommendation.entity.Member;
import spotify.recommendation.entity.Song;
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

    @GetMapping("/songs/list")
    public String allSongs(Model model){
        List<Song> songs = songService.allFind();
        model.addAttribute("songs", songs);
        return "/songs/list";
    }

    @GetMapping("/songs/new")
    public String createForm(Model model) {
        model.addAttribute("songForm", new SongForm());
        return "songs/createSongForm";
    }

    @PostMapping("/songs/new")
    public String createRecommendation(@Valid SongForm form, @Login Member loginMember, BindingResult result, Model model){
        if(result.hasErrors()){
            return "songs/createSongForm";
        }
        int cluster = songService.findCluster(form.getSongName(), form.getArtist());
        memberService.savePreference(loginMember, cluster);
        List<SongDto> clusteredDto = songService.findClusteredDto(loginMember);
        model.addAttribute("clusteredDto", clusteredDto);
        return "songs/recommendation";

    }

    @GetMapping("/songs/save")
    public String createSaveForm(Model model) {
        model.addAttribute("songForm", new SongForm());
        return "songs/createSaveSongForm";
    }

    @PostMapping("/songs/save")
    public String saveSong(@Valid SongForm form, @Login Member loginMember, BindingResult result){
        if (result.hasErrors()) {
            return "songs/createSaveSongForm";
        }
        int cluster = loginMember.getPreferredCluster();
        songService.save(form.getSongName(), form.getArtist(), cluster, loginMember);

        return "redirect:/";
    }

    @GetMapping("/songs/delete")
    public String deleteSongForm(Model model){
        model.addAttribute("songForm", new SongForm());
        return "songs/deleteForm";
    }

    @PostMapping("/songs/delete")
    public String deleteSong(@Valid SongForm songForm, @Login Member loginMember, BindingResult result){
        if (result.hasErrors()) {
            return "songs/deleteForm";
        }
        String artist = songForm.getArtist();
        String songName = songForm.getSongName();
        songService.delete(songName, artist, loginMember);
        return "redirect:/";
    }

}
