package com.springjohn.springgraphqlsample.repository;

import com.springjohn.springgraphqlsample.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

    Book findByIsn(String isn);
}
