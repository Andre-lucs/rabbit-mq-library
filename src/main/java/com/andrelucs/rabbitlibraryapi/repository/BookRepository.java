package com.andrelucs.rabbitlibraryapi.repository;

import com.andrelucs.rabbitlibraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitle(String title);
}
