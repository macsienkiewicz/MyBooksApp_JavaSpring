package com.mybooksapp_javaspring.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    ResponseEntity<List<BookDTO>> getBooks() {
        List<BookDTO> books = bookService.getBooks();
        if (books.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{id}")
    ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO dto) {
        BookDTO savedBook = bookService.saveBook(dto);
        URI savedBookUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();
        return ResponseEntity.created(savedBookUri).body(savedBook);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PatchMapping("/{id}")
    ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody BookDTO book) {
        return bookService.updateBook(id, book)
                .map(offer -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }


}
