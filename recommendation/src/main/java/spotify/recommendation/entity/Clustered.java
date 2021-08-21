package spotify.recommendation.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Clustered {

    @NotNull
    @Column(name = "song_name")
    private String songName;

    @NotNull
    @Id
    private String artist;

    @NotNull
    private int cluster_id;

    private Long stream;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
