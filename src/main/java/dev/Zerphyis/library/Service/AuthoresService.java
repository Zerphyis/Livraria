package dev.Zerphyis.library.Service;

import dev.Zerphyis.library.Entity.Author.Author;
import dev.Zerphyis.library.Entity.Datas.DataAuthor;
import dev.Zerphyis.library.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthoresService {

    @Autowired
    private AuthorRepository repository;

    public Author registerAuthor(DataAuthor data) {
        Author newAuthor = new Author(data);
        return repository.save(newAuthor);
    }

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return repository.findById(id);
    }

    public Optional<Author> updateAuthor(Long id, DataAuthor data) {
        return repository.findById(id).map(author -> {
            author.setName(data.name());
            author.setNationality(data.nationality());
            author.setDateBirth(data.dateBirth());
            return repository.save(author);
        });
    }

    public void deleteAuthor(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Autor com ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }
}
