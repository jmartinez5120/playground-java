package com.springjohn.springgraphqlsample.service;

import com.springjohn.springgraphqlsample.service.datafetcher.AllBooksDataFetcher;
import com.springjohn.springgraphqlsample.service.datafetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
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

@Service
public class GraphQLService {

    // Loads the graphql file from the resource folder
    @Value("classpath:books.graphql")
    private Resource resource;

    private GraphQL graphQL;

    // Determines how the data gets fetched from the database.
    private final AllBooksDataFetcher allBooksDataFetcher;

    // Determines how the data gets fetched from the database.
    private final BookDataFetcher bookDataFetcher;

    @Autowired
    public GraphQLService(AllBooksDataFetcher allBooksDataFetcher, BookDataFetcher bookDataFetcher) {
        this.allBooksDataFetcher = allBooksDataFetcher;
        this.bookDataFetcher = bookDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        //get schema
        File schemaFile = resource.getFile();

        // parse schema
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allBooks", allBooksDataFetcher)
                        .dataFetcher("book", bookDataFetcher)
                        )
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }

}

