package gijin.prac;

import gijin.prac.domain.Author;
import gijin.prac.repository.AuthorMemoryRepository;
import gijin.prac.repository.AuthorRepository;
import gijin.prac.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig
{
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public AuthorService authorService()
    {
        return new AuthorService(authorRepository());
    }
    @Bean
    public AuthorRepository authorRepository()
    {
        return new AuthorMemoryRepository(dataSource);
    }

}
