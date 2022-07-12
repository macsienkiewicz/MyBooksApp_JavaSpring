package com.mybooksapp_javaspring.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class BookService {
    private final BookRepository bookRepository;
    private final BookDTOMapper bookDTOMapper;

    public BookService(BookRepository bookRepository, BookDTOMapper bookDTOMapper) {
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
    }

    List<BookDTO> getBooks() {
       List<Book> books = (List<Book>) bookRepository.findAll();
       List<BookDTO> booksDTO = new ArrayList<>();
       for (Book book: books) {
           BookDTO bookDTO = bookDTOMapper.map(book);
           booksDTO.add(bookDTO);
       }
       return booksDTO;
    }

    Optional<BookDTO> getBookById(int id) {
        return bookRepository.findById(id)
                .map(bookDTOMapper::map);
    }

    BookDTO saveBook(BookDTO dto) {
        Book bookToSave = bookDTOMapper.map(dto);
        Book savedBook = bookRepository.save(bookToSave);
        return bookDTOMapper.map(savedBook);
    }

    @Transactional
    public Optional<BookDTO> updateBook(int id, BookDTO bookDTO) {
        return bookRepository.findById(id)
                .map(target -> setEntityFields(bookDTO, target))
                .map(bookDTOMapper::map);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    private Book setEntityFields(BookDTO source, Book target) {
        if (source.getName() != null) {
            target.setName(source.getName());
        }
        if (source.getYearOfPublication() > 0) {
            target.setYearOfPublication(source.getYearOfPublication());
        }
        return target;
    }
}
