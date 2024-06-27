package patalanocarlo.U5W2D3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import patalanocarlo.U5W2D3.Entities.Author;
import patalanocarlo.U5W2D3.Exceptions.ResourceNotFoundException;
import patalanocarlo.U5W2D3.Services.AuthorService;
import patalanocarlo.U5W2D3.Services.CloudinaryService;

import java.io.IOException;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return authorService.findAll(pageable);
    }

    @PostMapping
    public Author createAuthor(@RequestBody @Validated Author author) {
        if (author.getId() != null && authorService.findById(author.getId()).isPresent()) {
            throw new RuntimeException("Autore già esistente: " + author.getId());
        }
        return authorService.save(author);
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable String id) {
        Long authorId;
        try {
            authorId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("ID autore non valido");
        }
        return authorService.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Autore non trovato con ID: " + authorId));
    }

    @PostMapping("/{id}/avatar")
    public ResponseEntity<String> updateAuthorAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            String avatarUrl = cloudinaryService.uploadAvatar(file);

            Author author = authorService.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Autore non trovato con ID: " + id));

            author.setAvatar(avatarUrl);
            authorService.save(author);

            return ResponseEntity.ok().body("Avatar aggiornato con successo per l'autore con ID: " + id);
        } catch (IOException e) {
            e.printStackTrace(); // Assicurati di vedere gli eventuali log dell'errore
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiornamento dell'avatar");
        }
    }
}