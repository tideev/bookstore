package hh.sof03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    //haetaan kategoriat
      @GetMapping(value="/categorylist")
    public String getAllCategories(Model model) {

        model.addAttribute("categories", categoryRepository.findAll()); 
        return "categorylist";
    }

    //Lisätään kategoria
     @RequestMapping(value = "/addcategory")
    public String addCategory(Model model){
    	model.addAttribute("category", new Category());
        return "addcategory";
    }   

    //Tallennetaan
     @PostMapping(value = "/savecategory")
    public String saveCategory(@ModelAttribute("category") Category category) {
     
        categoryRepository.save(category);
        return "redirect:/categorylist";
    }

    //Poistetaan kategoriia
    @GetMapping(value = "/deleteCategory/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Long categoryId, Model model) {
    	categoryRepository.deleteById(categoryId);
        return "redirect:../categorylist";
    }     
    
}
