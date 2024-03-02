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
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String showPeople(Model model){
        model.addAttribute("people", personDAO.showAllPeople());
        return "people/peopleList";
    }
    @GetMapping("/{id}")
    public String aboutPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("books", bookDAO.showPersonBook(id));
        model.addAttribute("person", personDAO.showOnePerson(id));
        return "people/aboutPerson";
    }
    @GetMapping("/new")
    public String showCreatePerson(Model model){
        model.addAttribute("person", new Person());
        return "people/newPerson";
    }
    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "people/newPerson";
        personDAO.createPerson(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/update")
    public String updatePage(Model model,@PathVariable("id") int id){
        model.addAttribute("person", personDAO.showOnePerson(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()) return "people/edit";
        personDAO.updatePerson(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/people";
    }
}
