package com.mybooksapp_javaspring.author;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/authors")
class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    ResponseEntity<List<AuthorDTO>> getAuthors() {
        List<AuthorDTO> authors = authorService.getAuthors();
        if (authors.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authors);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{id}")
    ResponseEntity<AuthorDTO> getAuthorById(@PathVariable int id) {
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    ResponseEntity<AuthorDTO> saveAuthor(@RequestBody AuthorDTO author) {
        AuthorDTO savedAuthor = authorService.saveAuthor(author);
        URI savedAuthorUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAuthor.getId())
                .toUri();
        return ResponseEntity.created(savedAuthorUri).body(savedAuthor);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/{id}")
    ResponseEntity<?> replaceAuthor(@PathVariable int id, @RequestBody AuthorDTO author) {
        return authorService.replaceAuthor(id, author)
                .map(c -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}
