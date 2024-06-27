package patalanocarlo.U5W2D3.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Deve esserci perforza un nome inserito")
    private String name;
    @NotBlank(message = "Deve esserci perforza un Cognome inserito")
    private String cognome;
    @Email(message = "Email non valida.")
    @NotBlank(message = "L'email non può essere vuota")
    private String email;
    @NotBlank(message = "La data di nascita non può essere vuota")
    private String dataDiNascita;

    private String avatar;

    public Author(String nome, String cognome, String email, String dataDiNascita, String avatar) {
        this.name = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        this.avatar = null;
    }
}