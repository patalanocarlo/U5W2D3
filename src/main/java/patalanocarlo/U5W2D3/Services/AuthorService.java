package patalanocarlo.U5W2D3.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import patalanocarlo.U5W2D3.Entities.Author;
import patalanocarlo.U5W2D3.Repository.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> update(Long id, Author updatedAuthor) {
        return authorRepository.findById(id).map(author -> {
            author.setName(updatedAuthor.getName());
            author.setCognome(updatedAuthor.getCognome());
            author.setEmail(updatedAuthor.getEmail());
            author.setDataDiNascita(updatedAuthor.getDataDiNascita());
            author.setAvatar("https://ui-avatars.com/api/?name=" + updatedAuthor.getName() + "+" + updatedAuthor.getCognome());
            return authorRepository.save(author);
        });
    }

    public boolean deleteById(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}