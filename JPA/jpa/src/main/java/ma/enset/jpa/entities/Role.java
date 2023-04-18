package ma.enset.jpa.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "DESCRIPTION")
    private String desc ;
    @Column(unique = true)
    private String roleName ;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER) @ToString.Exclude
    private List<User> users=new ArrayList<>();
}
