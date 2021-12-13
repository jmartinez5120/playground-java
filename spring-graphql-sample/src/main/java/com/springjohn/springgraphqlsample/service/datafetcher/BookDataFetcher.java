package com.springjohn.springgraphqlsample.service.datafetcher;

import com.springjohn.springgraphqlsample.model.Book;
import com.springjohn.springgraphqlsample.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    private final BookRepository bookRepository;

    @Autowired
    public BookDataFetcher(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        String isn = dataFetchingEnvironment.getArgument("id");
        return bookRepository.findByIsn(isn);
    }
}
