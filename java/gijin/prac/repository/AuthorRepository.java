package gijin.prac.repository;

import gijin.prac.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository
{
    Author save(Author author);
    Optional<Author> findById(Long id);
    Optional<Author> findByName(String name);
    Optional<Author> findByDate(String date);
    List<Author>findAll();
}
