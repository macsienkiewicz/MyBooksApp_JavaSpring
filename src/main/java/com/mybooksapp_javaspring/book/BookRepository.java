package com.mybooksapp_javaspring.book;

import org.springframework.data.repository.CrudRepository;

interface BookRepository extends CrudRepository<Book, Integer> {
}
