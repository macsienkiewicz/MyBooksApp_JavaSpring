package com.mybooksapp_javaspring.author;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorDTOMapper authorDTOMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorDTOMapper authorDTOMapper) {
        this.authorRepository = authorRepository;
        this.authorDTOMapper = authorDTOMapper;
    }

    List<AuthorDTO> getAuthors() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        for (Author author: authors) {
            AuthorDTO authorDTO = authorDTOMapper.map(author);
            authorsDTO.add(authorDTO);
        }
        return authorsDTO;
    }

    Optional<AuthorDTO> getAuthorById(int id) {
        return authorRepository.findById(id).map(authorDTOMapper::map);
    }

    AuthorDTO saveAuthor(AuthorDTO authorDTO) {
        Author author = authorDTOMapper.map(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorDTOMapper.map(savedAuthor);
    }

    Optional<AuthorDTO> replaceAuthor(int id, AuthorDTO dto) {
        if(!authorRepository.existsById(id)) {
            return Optional.empty();
        }
        dto.setId(id);
        Author authorToUpdate = authorDTOMapper.map(dto);
        Author updatedEntity = authorRepository.save(authorToUpdate);
        return Optional.of(authorDTOMapper.map(updatedEntity));
    }

    void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }



}
