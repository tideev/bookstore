package hh.sof03.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;


@CrossOrigin
@Controller
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    //Haetaan kaikki kirjat restillä
    @GetMapping(value="/books")
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) bookRepository.findAll();
    }
    
    //Haetaan kirja id:llä restillä
    @GetMapping(value="/books/{id}")
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
        return bookRepository.findById(id);
    }
    
    //Lisätään kirja restillä
    @PostMapping(value="/books")
    public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
    	return bookRepository.save(book);
    }
    

}
