package patalanocarlo.U5W2D3.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La Categoria Non può essere vuota")
    private String categoria;

    @NotBlank(message = "Il titolo Non può essere vuoto ")
    private String titolo;

    @NotBlank(message = "Devi sempre inserire una cover")
    private String cover;
    @NotBlank(message = "Non hai inserito un contenuto valido")
    private String contenuto;
    @NotNull(message = "Il tempo di lettura non può essere vuoto")
    @Min(value = 1, message = "Il tempo di lettura deve essere maggiore di zero")
    private Integer tempoDiLettura;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull(message = "Inserisci l id di un autore")
    private Author author;

    public BlogPost(String categoria, String titolo, String cover, String contenuto, int tempoDiLettura, Author author) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.author = author;
    }
}