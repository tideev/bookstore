package hh.sof03.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner studentDemo(BookRepository bookRepository){
		return (args) -> {
			log.info("save a couple of books");
			bookRepository.save(new Book ("Twilight","Stephenie Meyer", "978-0-316-16017-9", 2005, 11.99));
			bookRepository.save(new Book ("New Moon","Stephenie Meyer", "0-316-16019-9", 2006, 13.99));
			bookRepository.save(new Book ("Eclipse","Stephenie Meyer", "978-0-316-16020-9", 2007, 14.99));
			

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
