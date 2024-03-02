package library.controllers;

import jakarta.validation.Valid;
import library.dao.BookDAO;
import library.dao.PersonDAO;
import library.model.Book;
import library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String BooksPage(Model model){
        model.addAttribute("books", bookDAO.showAllBooks());
        return "books/booksList";
    }
    @GetMapping("/new")
    public String newBookPage(Model model){
        model.addAttribute("book", new Book());
        return "books/newBook";
    }
    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        Optional<Person> bookOwner = bookDAO.getBookOwner(id);
        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        }
        else {
            model.addAttribute("people", personDAO.showAllPeople());
        }
        model.addAttribute("book", bookDAO.showOneBook(id));
        return "books/aboutBook";
    }
    @PostMapping()
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "books/newBook";
        bookDAO.createBook(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/update")
    public String updatePage(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.showOneBook(id));
        return "books/editBook";
    }
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()) return "books/editBook";
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
    @PostMapping("/{id}")
    public String appointBook(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.appointBook(person, bookDAO.showOneBook(id));
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id){
        bookDAO.releaseBook(id);
        return "redirect:/books/" + id;
    }
}
