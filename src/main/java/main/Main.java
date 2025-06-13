package main;

import model.BookInfo;
import model.DataBook;
import model.DataTotal;
import service.BookApiClient;
import service.DataConverter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    // paginas totales https://gutendex.com/books/?page=2378
    private static final String URL_BASE = "https://gutendex.com/books/";
    private BookApiClient bookApiClient = new BookApiClient();
    private DataConverter dataConverter = new DataConverter();
    private Scanner lecture = new Scanner(System.in);

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

        // Extrae los datos de clase BookInfo para mostrar los libros mas descargados
        System.out.println("\n=========== Top 15 de libros descargados ===========");
        books.stream()
                .sorted(Comparator.comparing(BookInfo::getNumbreDownload).reversed())
                .limit(15)
                .forEach(bookInfo -> System.out.println(
                        "Título: " + bookInfo.getTitle() +
                                " Total descargas: " + bookInfo.getNumbreDownload()));

        // Buscar un libro por su nombre
        System.out.print("\nIngrese el nombre del libro que desea buscar: ");
        String searchBook = lecture.nextLine();
        json = bookApiClient.fetchBooks(URL_BASE + "?search=" + searchBook.replace(" ", "+"));
        data = dataConverter.dataConverter(json, DataTotal.class);
        Optional<DataBook> foundBooks = data.booksList().stream()
                .filter(book -> book.title().toLowerCase().contains(searchBook.toLowerCase()))
                .findFirst();
        if (foundBooks.isPresent()){
            System.out.println("\nID: " + foundBooks.get().id() +
                    "\nTítulo: " + foundBooks.get().title() +
                    "\nAutor: " + foundBooks.get().autorList().stream().map(a->a.authorName()).collect(Collectors.joining(", ")) +
                    "\nLenguaje: " + foundBooks.get().languages() +
                    "\nTotal de descargar: " + foundBooks.get().numberDownload());
        } else {
            System.out.println("No se ha encontrado resultados");
        }

    }
}
