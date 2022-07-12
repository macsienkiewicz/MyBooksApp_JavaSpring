package com.mybooksapp_javaspring.book;

import com.mybooksapp_javaspring.author.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
class BookDTOMapper {
    private final AuthorRepository authorRepository;

    public BookDTOMapper(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    BookDTO map(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setYearOfPublication(book.getYearOfPublication());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setAuthorName(book.authorName());
        return dto;
    }

    Book map(BookDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setYearOfPublication(dto.getYearOfPublication());
        authorRepository.findById(dto.getAuthorId())
                .ifPresent(book::setAuthor);
        return book;
    }

}
