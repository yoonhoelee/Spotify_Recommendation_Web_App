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

    public void save(String songName, String artist, int cluster, @Login Member loginMember){

        Song song = new Song(songName, artist, cluster, loginMember);
        songRepository.save(song);
    }

    public SongDto entityToDto(Song song){

        SongDto dto = new SongDto();
        dto.setArtist(song.getArtist());
        dto.setSongName(song.getSongName());
        return dto;

    }
}
