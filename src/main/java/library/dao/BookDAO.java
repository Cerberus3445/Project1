package library.dao;

import library.model.Book;
import library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> showAllBooks(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book showOneBook(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void createBook(Book book){
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
    }
    public void updateBook(int id,Book book){
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?", book.getTitle(), book.getAuthor(), book.getYear(), id);
    }
    public void deleteBook(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
    public void appointBook(Person person, Book book){
        jdbcTemplate.update("UPDATE Book SET person_id=? where id=?", person.getId(), book.getId());
    }
    public void releaseBook(int book_id){
        jdbcTemplate.update("UPDATE book SET person_id = NULL where id=?", book_id);
    }
    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                        "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    public List<Book> showPersonBook(int id){
        return jdbcTemplate.query("select * from person join book on person.id = book.person_id where person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
