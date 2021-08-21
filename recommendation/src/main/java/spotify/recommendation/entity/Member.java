package spotify.recommendation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private UUID id;

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
}
