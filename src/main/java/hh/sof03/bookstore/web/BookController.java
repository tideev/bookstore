package hh.sof03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;


@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    
    @GetMapping(value="/bookstore")
    public String getAllBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll()); 
        return "bookstore";
    }

    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        return "addbook";
    }   
    
    @PostMapping(value = "/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        if (book.getId() == null) {
            // Jos kirjalla ei ole ID:tä, se on uusi kirja, joten tallennetaan se
            bookRepository.save(book);
        } else {
            // Haetaan kirja tietokannasta sen ID:n perusteella
            Book existingBook = bookRepository.findById(book.getId()).orElse(null);
    
            if (existingBook != null) {
                // Päivitetään kirjan tiedot muokatulla kirjalla
                existingBook.setTitle(book.getTitle());
                existingBook.setAuthor(book.getAuthor());
                existingBook.setYear(book.getYear());
                existingBook.setIsbn(book.getIsbn());
                existingBook.setPrice(book.getPrice());
                // Tallennetaan päivitetty kirja tietokantaan
                bookRepository.save(existingBook);
            }
        }
    
        // Ohjataan käyttäjä takaisin kirjaluettelosivulle
        return "redirect:/bookstore";
    }
   

    @GetMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        // Haetaan kirja tietokannasta sen ID:n perusteella
        Book book = bookRepository.findById(bookId).orElse(null);

        if (book != null) {
            // Lähetetään kirja editbook.html -sivulle muokattavaksi
            model.addAttribute("book", book);
            return "editbook";
        } else {
            // Käsitellään tilanne, jossa kirjaa ei löydy
            return "redirect:/bookstore"; // Ohjataan kirjaluettelosivulle
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookRepository.deleteById(bookId);
        return "redirect:../bookstore";
    }     
    
}
