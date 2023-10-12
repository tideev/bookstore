package hh.sof03.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bookstore.web.BookController;
import hh.sof03.bookstore.web.BookRestController;
import hh.sof03.bookstore.web.CategoryController;
import hh.sof03.bookstore.web.CategoryRestController;

@ExtendWith(SpringExtension.class) 
@SpringBootTest
class BookstoreApplicationTests {

	
	@Autowired
	private BookController bController;

	@Autowired
	private BookRestController bRestController;

	@Autowired
	private CategoryController cController;

	@Autowired
	private CategoryRestController cRestController;

	@Test
	public void contextLoads() {
		assertThat(bController).isNotNull();
		assertThat(bRestController).isNotNull();
		assertThat(cController).isNotNull();
		assertThat(cRestController).isNotNull();
	}

}
