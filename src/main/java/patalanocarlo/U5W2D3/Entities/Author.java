package patalanocarlo.U5W2D3.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cognome;
    private String email;
    private String dataDiNascita;
    private String avatar;

    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private List<BlogPost> blogPosts;

    public Author(String nome, String cognome, String email, String dataDiNascita, String avatar) {
        this.name = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        this.avatar = "https://ui-avatars.com/api/?name=" + nome  + cognome;
    }
    }