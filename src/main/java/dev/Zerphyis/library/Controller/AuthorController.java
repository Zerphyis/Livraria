package dev.Zerphyis.library.Controller;

import dev.Zerphyis.library.Entity.Author.Author;
import dev.Zerphyis.library.Entity.Datas.DataAuthor;
import dev.Zerphyis.library.Exeception.AuthorNotFoundExeception;
import dev.Zerphyis.library.Service.AuthoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AuthorController {
    @Autowired
    private AuthoresService service;

    @PostMapping
    public ResponseEntity<Author> register(@RequestBody  DataAuthor data) {
        Author author = service.registerAuthor(data);
        return ResponseEntity.ok(author);
    }

    @GetMapping()
    public ResponseEntity<List<Author>> listAll() {
        List<Author> authors = service.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> listById(@PathVariable("id") Long id) {
        Optional<Author> author = service.getAuthorById(id);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> uptade(@PathVariable("id") Long id,@RequestBody DataAuthor data) {
        Optional<Author> updatedAuthor = service.updateAuthor(id, data);
        return updatedAuthor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable("id") Long id) {
        service.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(AuthorNotFoundExeception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAuthorNotFoundException(AuthorNotFoundExeception ex) {
        return ex.getMessage();
    }
}

