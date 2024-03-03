package library.dao;

import library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> showAllPeople(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person showOnePerson(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public void createPerson(Person person){
        jdbcTemplate.update("INSERT INTO Person(name, age) VALUES(?, ?)", person.getName(), person.getAge());
    }
    public void updatePerson(int id,Person person){
        jdbcTemplate.update("UPDATE person SET name=?, age=? WHERE id=?", person.getName(), person.getAge(), id);
    }
    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
