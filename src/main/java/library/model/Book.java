package library.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class Book {
    private int id;
    private int user_id;
    @NotEmpty(message = "Title cannot be null")
    private String title;
    @NotEmpty(message = "Author cannot be null")
    private String author;
    @Min(value = 0, message = "Year should be greater than 0")
    private int year;
    public Book(int id,String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }
    public Book(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return title + " " + author + " " + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && user_id == book.user_id && year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, title, author, year);
    }
}
