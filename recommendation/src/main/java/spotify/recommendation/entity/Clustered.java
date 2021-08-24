package spotify.recommendation.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Clustered {

    @Id @GeneratedValue
    @Column(name = "song_id")
    private Long id;

    @NotNull
    private String songName;

    @NotNull
    private String artist;

    @NotNull
    private int cluster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Clustered(String songName, String artist, int cluster, Member member) {
        this.songName = songName;
        this.artist = artist;
        this.cluster = cluster;
        this.member = member;
    }
}
