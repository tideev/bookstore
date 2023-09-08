package hh.sof03.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import hh.sof03.bookstore.domain.Book;


@Controller
public class BookController {
    
    @GetMapping(value="/index")
    public String getAllBooks(Model model) {
        model.addAttribute("book", new Book());
        return "bookstore";
    }
    
}
