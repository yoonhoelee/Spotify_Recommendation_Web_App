package spotify.recommendation.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "Clustered")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Clustered {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @NotNull
    @Column(name = "song_name")
    private String songName;

    @NotNull
    private String artist;

    @NotNull
    private int cluster_id;

    private Long stream;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
