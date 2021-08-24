package spotify.recommendation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import spotify.recommendation.SessionConst;
import spotify.recommendation.dto.ClusteredDto;
import spotify.recommendation.entity.Clustered;
import spotify.recommendation.entity.Member;
import spotify.recommendation.login.argumentresolver.Login;
import spotify.recommendation.repository.ClusteredRepository;
import spotify.recommendation.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClusteredService {

    private final ClusteredRepository clusteredRepository;

    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    Member loginMember = (Member) request.getAttribute(SessionConst.LOGIN_MEMBER);

    public List<ClusteredDto> findClusteredDto(){

        int cluster = loginMember.getPreferredCluster();
        List<Clustered> clusteredList = clusteredRepository.findByCluster(cluster);
        return clusteredList.stream().map(x -> entityToDto(x)).collect(Collectors.toList());

    }

    public int findCluster(String songName, String artist){
        return clusteredRepository.findClusteredByNameAndArtist(songName, artist);
    }

    public void save(String songName, String artist){
        Clustered clustered = new Clustered(songName, artist, loginMember.getPreferredCluster(), loginMember);
        clusteredRepository.save(clustered);
    }

    public ClusteredDto entityToDto(Clustered clustered){

        ClusteredDto dto = new ClusteredDto();
        dto.setArtist(clustered.getArtist());
        dto.setSongName(clustered.getSongName());
        return dto;

    }
}
