package hh.sof03.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository){
		return (args) -> {
			log.info("save a couple of books");
			Category category1 = new Category ("Scifi");
			categoryRepository.save(category1);
			Category category2 = new Category ("Fiction");
			categoryRepository.save(category2);
			Category category3 = new Category ("Biography");
			categoryRepository.save(category3);
			Category category4 = new Category ("Non-Fiction");
			categoryRepository.save(category4);

			bookRepository.save(new Book ("Twilight","Stephenie Meyer", "978-0-316-16017-9", 2005, 11.99, category2));
			bookRepository.save(new Book ("New Moon","Stephenie Meyer", "0-316-16019-9", 2006, 13.99, category2));
			bookRepository.save(new Book ("Eclipse","Stephenie Meyer", "978-0-316-16020-9", 2007, 14.99, category2));
			bookRepository.save(new Book ("A Beatiful Mind","Sylvia Nasar", "0-684-81906-6", 1998, 16.99, category3));
			

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
		
	}


}
