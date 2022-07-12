package com.mybooksapp_javaspring.author;

import org.springframework.stereotype.Service;

@Service
class AuthorDTOMapper {
    AuthorDTO map(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setSurname(author.getSurname());
        dto.setNationality(author.getNationality());
        dto.setYearOfBirth(author.getYearOfBirth());
        dto.setYearOfDeath(author.getYearOfDeath());
        return dto;
    }

    Author map(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setSurname(dto.getSurname());
        author.setNationality(dto.getNationality());
        author.setYearOfBirth(dto.getYearOfBirth());
        author.setYearOfDeath(dto.getYearOfDeath());
        return author;
    }
}
