package patalanocarlo.U5W2D3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import patalanocarlo.U5W2D3.Entities.Author;
import patalanocarlo.U5W2D3.Exceptions.ResourceNotFoundException;
import patalanocarlo.U5W2D3.Services.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return authorService.findAll(pageable);
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        if (author.getId() != null && authorService.findById(author.getId()).isPresent()) {
            throw new RuntimeException("ID autore già esistente: " + author.getId());
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

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        authorService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autore non trovato con ID: " + id));
        return authorService.update(id, updatedAuthor)
                .orElseThrow(() -> new ResourceNotFoundException("Autore non trovato"));
    }
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        if (!authorService.deleteById(id)) {
            throw new ResourceNotFoundException("Autore non trovato");
        }
    }
}
