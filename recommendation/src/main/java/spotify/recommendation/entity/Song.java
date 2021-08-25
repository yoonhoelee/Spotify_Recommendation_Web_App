package spotify.recommendation.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Song {

    @Id @GeneratedValue
    @Column(name = "song_id")
    private Long id;

    @NotEmpty
    @Column(name = "song_name")
    private String songName;

    @NotEmpty
    private String artist;

    @NotEmpty
    private int cluster;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getSongs().add(this);
    }

    public Song(String songName, String artist, int cluster, Member member) {
        this.songName = songName;
        this.artist = artist;
        this.cluster = cluster;
        this.member = member;
    }
}
