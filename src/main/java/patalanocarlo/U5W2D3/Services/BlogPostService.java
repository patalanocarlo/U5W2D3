package patalanocarlo.U5W2D3.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import patalanocarlo.U5W2D3.Entities.BlogPost;
import patalanocarlo.U5W2D3.Repository.BlogPostRepository;

import java.util.Optional;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    public Page<BlogPost> findAll(Pageable pageable) {
        return blogPostRepository.findAll(pageable);
    }

    public Optional<BlogPost> findById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost save(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public Optional<BlogPost> update(Long id, BlogPost updatedBlogPost) {
        return blogPostRepository.findById(id).map(blogPost -> {
            blogPost.setCategoria(updatedBlogPost.getCategoria());
            blogPost.setTitolo(updatedBlogPost.getTitolo());
            blogPost.setCover(updatedBlogPost.getCover());
            blogPost.setContenuto(updatedBlogPost.getContenuto());
            blogPost.setTempoDiLettura(updatedBlogPost.getTempoDiLettura());
            blogPost.setAuthor(updatedBlogPost.getAuthor());
            return blogPostRepository.save(blogPost);
        });
    }

    public boolean deleteById(Long id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
            return true;
        }
        return false;
    }
}