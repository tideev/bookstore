package hh.sof03.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;

@ExtendWith(SpringExtension.class) 
@DataJpaTest
public class BookRepositoryTest {

    @Autowired 
    BookRepository bRepository;
    
    @Test  // testataan BookRepositoryn findByTitle()-metodin toimivuutta
    public void testFindByTitle() {
        List<Book> books = bRepository.findByTitle("Eclipse");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getIsbn()).isEqualTo("978-0-316-16020-9");
    }

    @Test // testataan BookRepositoryn save()-metodin toimivuutta
    public void testCreateNewBook(){
        Book book = new Book("Testbook", "Testauthor", "123-123-123", 2023, 25.00, null);
        bRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test // testataan BookRepositoryn delete()-metodin toimivuutta
    public void testDeleteBook (){
        Book book = new Book("Testbook", "Testauthor", "123-123-123", 2023, 25.00, null);
        bRepository.save(book);

        Long bookId = book.getId();
        assertThat(bRepository.findById(bookId)).isPresent();

        bRepository.deleteById(bookId);
        assertThat(bRepository.findById(bookId)).isEmpty();
    }

}
