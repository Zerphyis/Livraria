package dev.Zerphyis.library.Service;

import dev.Zerphyis.library.Entity.Author.Author;
import dev.Zerphyis.library.Entity.Books.Books;
import dev.Zerphyis.library.Entity.Datas.Books.DataBooksEntry;
import dev.Zerphyis.library.Entity.Datas.Books.DataBooksExit;
import dev.Zerphyis.library.Repository.AuthorRepository;
import dev.Zerphyis.library.Repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BooksService {
    @Autowired
    private BooksRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    public DataBooksExit registerBook(DataBooksEntry data) {
        Author author = authorRepository.findById(data.authorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Books newBook = new Books(data.title(), data.publicationDate(), data.publisher(), data.gender(), data.quantityAvailable(),author);

        newBook = repository.save(newBook);

        return new DataBooksExit(
                newBook.getTitle(),
                newBook.getAuthor().getName(),
                newBook.getPublicationDate(),
                newBook.getPublisher(),
                newBook.getGender(),
                newBook.getQuantityAvailable()
        );
    }

    public List<DataBooksExit> getAllBooks() {
        List<Books> books = repository.findAll();
        return books.stream().map(book -> new DataBooksExit(
                book.getTitle(),
                book.getAuthor().getName(),
                book.getPublicationDate(),
                book.getPublisher(),
                book.getGender(),
                book.getQuantityAvailable()
        )).collect(Collectors.toList());
    }

    public Optional<DataBooksExit> getBookById(Long id) {
        Optional<Books> book = repository.findById(id);
        return book.map(b -> new DataBooksExit(
                b.getTitle(),
                b.getAuthor().getName(),
                b.getPublicationDate(),
                b.getPublisher(),
                b.getGender(),
                b.getQuantityAvailable()
        ));
    }

    public Optional<DataBooksExit> updateBook(Long id, DataBooksEntry data) {
        return repository.findById(id).map(book -> {
            book.setTitle(data.title());
            book.setAuthor(authorRepository.findById(data.authorId()).orElseThrow(() -> new RuntimeException("Autor não encontrado")));
            book.setPublicationDate(data.publicationDate());
            book.setPublisher(data.publisher());
            book.setGender(data.gender());
            book.setQuantityAvailable(data.quantityAvailable());
            repository.save(book);
            return new DataBooksExit(
                    book.getTitle(),
                    book.getAuthor().getName(),
                    book.getPublicationDate(),
                    book.getPublisher(),
                    book.getGender(),
                    book.getQuantityAvailable()
            );
        });
    }

    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Livro com ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }
}
