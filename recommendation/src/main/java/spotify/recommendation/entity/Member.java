package spotify.recommendation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
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

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Clustered> cluster = new ArrayList<>();

    public Member(String email, String password, String name, int age, Sex sex) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

}
