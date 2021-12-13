package com.springjohn.springgraphqlsample.service;

import com.springjohn.springgraphqlsample.model.Book;
import com.springjohn.springgraphqlsample.repository.BookRepository;
import com.springjohn.springgraphqlsample.service.datafetcher.AllBooksDataFetcher;
import com.springjohn.springgraphqlsample.service.datafetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    // Loads the graphql file from the resource folder
    @Value("classpath:books.graphql")
    private Resource resource;

    private GraphQL graphQL;

    private final BookRepository bookRepository;

    // Determines how the data gets fetched from the database.
    private final AllBooksDataFetcher allBooksDataFetcher;

    // Determines how the data gets fetched from the database.
    private final BookDataFetcher bookDataFetcher;

    @Autowired
    public GraphQLService(BookRepository bookRepository, AllBooksDataFetcher allBooksDataFetcher, BookDataFetcher bookDataFetcher) {
        this.bookRepository = bookRepository;
        this.allBooksDataFetcher = allBooksDataFetcher;
        this.bookDataFetcher = bookDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {

        // load books into the database for example
        loadDataIntoHSQL();

        //get schema
        File schemaFile = resource.getFile();

        // parse schema
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {

        Stream.of(
                new Book("11232344556767", "Lord of the Rings", "Token", "12/05/1990"),
                new Book("98723986986743", "Star Wars: Clone Wars", "Lukasfilms", "03/10/1990"),
                new Book("22739823097537", "Harry Potter", "J.K. Rollin", "07/05/1991"),
                new Book("438487y5987309", "Captain America", "S. Lee", "01/14/1962")
        ).forEach(bookRepository::save);
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allBooks", allBooksDataFetcher)
                        .dataFetcher("book", bookDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
    
    

}

