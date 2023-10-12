package hh.sof03.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
     CategoryRepository cRepository;

    @Test//testataan CategoryRepon save()- ja findByName()-metodien toimivuutta
    public void testCreateAndSearchCategory() {
        Category category = new Category("Test Category");
        cRepository.save(category);
        assertThat(category.getCategoryId()).isNotNull();

        List<Category> categories = cRepository.findByName("Test Category");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Test Category");
    }

    @Test  // testataan CategoryRepon delete()-metodin toimivuutta
    public void testDeleteCategory() {
        Category category = new Category("Test Category");
        cRepository.save(category);

        Long categoryId = category.getCategoryId();
        assertThat(cRepository.findById(categoryId)).isPresent();

        cRepository.deleteById(categoryId);
        assertThat(cRepository.findById(categoryId)).isEmpty();
    }
}
