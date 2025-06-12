package main;

import model.BookInfo;
import model.DataBook;
import model.DataTotal;
import service.BookApiClient;
import service.DataConverter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    // paginas totales https://gutendex.com/books/?page=2378
    private static final String URL_BASE = "https://gutendex.com/books/?page=1000";
    private BookApiClient bookApiClient = new BookApiClient();
    private DataConverter dataConverter = new DataConverter();

    public void showMenu(){
        var json = bookApiClient.fetchBooks(URL_BASE);
        System.out.println(json);
        var data = dataConverter.dataConverter(json, DataTotal.class);

        /*
        data.booksList().stream()
                .limit(10)
                .forEach(book -> {
                    System.out.println(book.title());
                    book.autorList().forEach(author -> {
                        System.out.println(author.authorName());
                    });
                });
        */

        // Transforma los datos de los record DataBook y DataAuthor en solo una clase llamada BookInfo
        List<BookInfo> books = data.booksList().stream()
                .flatMap(dataBook -> dataBook.autorList().stream()
                        .map(dataAuthor -> new BookInfo(
                                dataBook.id(),
                                dataBook.title(),
                                dataAuthor.authorName(),
                                dataAuthor.birthYear(),
                                dataBook.numberDownload(),
                                dataBook.languages()
                        ))).collect(Collectors.toList());


        System.out.println("Top 15 de libros descargados");
        books.stream()
                .sorted(Comparator.comparing(BookInfo::getNumbreDownload).reversed())
                .limit(15)
                .forEach(bookInfo -> System.out.println(
                        "Título: " + bookInfo.getAuthorName() +
                                " Total descargas: " + bookInfo.getNumbreDownload()));
    }
}
