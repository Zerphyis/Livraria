package dev.Zerphyis.library.Controller;

import dev.Zerphyis.library.Entity.Books.Books;
import dev.Zerphyis.library.Entity.Datas.DataBooksEntry;
import dev.Zerphyis.library.Entity.Datas.DataBooksExit;
import dev.Zerphyis.library.Service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class BooksController {
    @Autowired
    private BooksService service;

    @PostMapping
    public ResponseEntity<DataBooksExit> register(@RequestBody DataBooksEntry data) {
        DataBooksExit bookResponse = service.registerBook(data);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping
    public ResponseEntity<List<DataBooksExit>> listAll() {
        List<DataBooksExit> books = service.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataBooksExit> listById(@PathVariable("id") Long id) {
        Optional<DataBooksExit> book = service.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataBooksExit> update(@PathVariable("id") Long id, @RequestBody DataBooksEntry data) {
        Optional<DataBooksExit> updatedBook = service.updateBook(id, data);
        return updatedBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
