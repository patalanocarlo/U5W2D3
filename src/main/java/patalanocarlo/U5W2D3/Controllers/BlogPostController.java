package patalanocarlo.U5W2D3.Controllers;

import lombok.Lombok;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import patalanocarlo.U5W2D3.Entities.Author;
import patalanocarlo.U5W2D3.Entities.BlogPost;
import patalanocarlo.U5W2D3.Exceptions.ResourceNotFoundException;
import patalanocarlo.U5W2D3.Services.AuthorService;
import patalanocarlo.U5W2D3.Services.BlogPostService;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<BlogPost> getAllBlogPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return blogPostService.findAll(pageable);
    }

    @PostMapping
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost) {
        Author existingAuthor = authorService.findById(blogPost.getAuthor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Autore non trovato con ID: " + blogPost.getAuthor().getId()));
        blogPost.setAuthor(existingAuthor);
        return blogPostService.save(blogPost);
    }

    @GetMapping("/{id}")
    public BlogPost getBlogPostById(@PathVariable String id) {
        Long postId;
        try {
            postId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("ID blog post non valido");
        }
        return blogPostService.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost non trovato con ID: " + postId));
    }
    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable Long id, @RequestBody BlogPost updatedBlogPost) {
        Author author = authorService.findById(updatedBlogPost.getAuthor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Autore non trovato"));
        updatedBlogPost.setAuthor(author);
        return blogPostService.update(id, updatedBlogPost)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost non trovato"));
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable Long id) {
        if (!blogPostService.deleteById(id)) {
            throw new ResourceNotFoundException("BlogPost non trovato");
        }
    }
}
