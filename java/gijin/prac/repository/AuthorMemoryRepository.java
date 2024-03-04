package gijin.prac.repository;

import gijin.prac.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AuthorMemoryRepository implements AuthorRepository
{
    private final JdbcTemplate jdbcTemplate;

    public AuthorMemoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Author> findByDate(String date) {
        List<Author> result= jdbcTemplate.query("select *from author where data=?",authorRowMapper(),date);
        return result.stream().findAny();
    }

    @Override
    public Author save(Author author) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("author").usingGeneratedKeyColumns("id");
        Map<String, Object> result = new HashMap<>();
        result.put("statement", author.getStatement());
        result.put("date", author.getDate());
        result.put("name", author.getName());
        Number number = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(result));
        author.setId(number.longValue()); // 이 부분이 수정되었습니다.
        return author;
    }
    @Override
    public Optional<Author> findById(Long id)
    {
        List<Author> result=jdbcTemplate.query("select *from author where id=?",authorRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Author> findByName(String name) {
        List<Author> result=jdbcTemplate.query("select *from author where name=?",authorRowMapper(),name);
        return result.stream().findAny();

    }

    @Override
    public List<Author> findAll()
    {
        return jdbcTemplate.query("select *from author",authorRowMapper());
    }
    private RowMapper<Author> authorRowMapper()
    {
        return new RowMapper<Author>() {
            @Override
            public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                Author author=new Author();
                author.setId(rs.getLong("id"));
                author.setName(rs.getString("name"));
                author.setDate(rs.getString("date"));
                author.setStatement(rs.getString("statement"));
                return author;

            }
        };
    }

}
