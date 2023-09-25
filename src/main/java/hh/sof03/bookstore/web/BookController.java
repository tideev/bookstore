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
import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;


@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository; // Lisätään injektio

    
    @GetMapping(value="/bookstore")
    public String getAllBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll()); 
        return "bookstore";
    }
  
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }   
    
    @PostMapping(value = "/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        if (book.getId() == null) {
            // Jos kirjalla ei ole ID:tä, se on uusi kirja, joten tallennetaan se
            // Uusi kirja, tallenna kategoria
        Long categoryId = book.getCategory().getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElse(null);
        book.setCategory(category);


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

                // Päivitetään kategoria
                Long categoryId = book.getCategory().getCategoryId();
                Category category = categoryRepository.findById(categoryId).orElse(null);
                existingBook.setCategory(category);
            

                // Tallennetaan päivitetty kirja tietokantaan
                bookRepository.save(existingBook);
            }
        }
        return "redirect:/bookstore";
    }
   

    @GetMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        // Haetaan kirja tietokannasta sen ID:n perusteella
        Book book = bookRepository.findById(bookId).orElse(null);

        if (book != null) {
            // Lähetetään kirja editbook.html -sivulle muokattavaksi
            model.addAttribute("book", book);
              model.addAttribute("categories", categoryRepository.findAll());
            return "editbook";
        } else {
            // Käsitellään tilanne, jossa kirjaa ei löydy
            return "redirect:/bookstore"; 
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookRepository.deleteById(bookId);
        return "redirect:../bookstore";
    }     
    
}
