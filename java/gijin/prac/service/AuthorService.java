package gijin.prac.service;

import gijin.prac.domain.Author;
import gijin.prac.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

public class AuthorService
{
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Long join(Author author)
    {
        checkAuthor(author);
        authorRepository.save(author);
        return author.getId();
    }
    public List<Author> findAuthors()
    {
        return authorRepository.findAll();
    }
    public Optional<Author> findDate(String date)
    {
        return authorRepository.findByDate(date);

    }
    public Optional<Author> findOne(Long id)
    {
        return authorRepository.findById(id);
    }
    private void checkAuthor(Author author)
    {
        authorRepository.findByName(author.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }


}
