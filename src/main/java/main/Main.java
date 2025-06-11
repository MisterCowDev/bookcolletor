package main;

import model.DataTotal;
import service.BookApiClient;
import service.DataConverter;

import java.util.stream.Collectors;

public class Main {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private BookApiClient bookApiClient = new BookApiClient();
    private DataConverter dataConverter = new DataConverter();

    public void showMenu(){
        var json = bookApiClient.fetchBooks(URL_BASE);
        System.out.println(json);
        var data = dataConverter.dataConverter(json, DataTotal.class);
        data.booksList().stream()
                .limit(5)
                .forEach(book -> {
                    System.out.println(book.title() + "  ");
                    book.autorList().forEach(author -> {
                        System.out.println(author.authorName());
                    });
                });

    }
}
