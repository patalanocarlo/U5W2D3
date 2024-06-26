package patalanocarlo.U5W2D3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patalanocarlo.U5W2D3.Entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
