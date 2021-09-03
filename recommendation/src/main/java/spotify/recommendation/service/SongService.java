package spotify.recommendation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spotify.recommendation.dto.SongDto;
import spotify.recommendation.entity.Member;
import spotify.recommendation.entity.Song;
import spotify.recommendation.login.argumentresolver.Login;
import spotify.recommendation.repository.SongRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SongService {

    private final SongRepository songRepository;

    public List<SongDto> findClusteredDto(@Login Member loginMember){

        int cluster = loginMember.getPreferredCluster();
        List<Song> clusteredList = songRepository.findByCluster(cluster);
        return clusteredList.stream().map(x -> entityToDto(x)).collect(Collectors.toList());

    }


    public int findCluster(String songName, String artist){
        return songRepository.findCluster(songName, artist);
    }

    public List<Song> allFind(){
        return songRepository.findAll();
    }

    public List<Song> findByMember(Long memberId){
        List<Song> listByMemberId = songRepository.joinFetch();
        return listByMemberId.stream().filter(s -> s.getMember().getId() == memberId).collect((Collectors.toList()));
    }

    public void save(String songName, String artist, int cluster, @Login Member loginMember){

        Song song = new Song(songName, artist, cluster, loginMember);
        if(songRepository.findBySongNameAndArtist(songName, artist)==null){
            songRepository.save(song);
        }
        else{
            throw new IllegalStateException("중복 곡이 아닌지 확인해주세요.");
        }
    }

    public void delete(String songName, String artist, @Login Member loginMember){
        Song song = songRepository.findBySongNameAndArtist(songName, artist);
        if(song.getMember().getId() == loginMember.getId()){
            songRepository.delete(song);
        }
        else{
            throw new IllegalStateException("본인이 추가한 곡인지 확인해주세요.");
        }
    }

    public SongDto entityToDto(Song song){

        SongDto dto = new SongDto();
        dto.setArtist(song.getArtist());
        dto.setSongName(song.getSongName());
        return dto;

    }
}
