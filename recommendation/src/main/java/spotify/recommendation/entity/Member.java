package spotify.recommendation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private int age;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Sex sex;

    private int preferredCluster;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Song> songs = new ArrayList<>();

    public Member(String email, String password, String name, int age, Sex sex) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

}
