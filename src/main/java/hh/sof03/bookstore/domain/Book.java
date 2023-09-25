package hh.sof03.bookstore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    @Column(name="publishing_year")
    private int year;
    private double price;

    @ManyToOne // Book @ManyToOne Categories
    @JoinColumn(name = "categoryId") //fk määritys Book-taulua varten
    private Category category;

     
    public Book() {
        this.title = null;
        this.author = null;
        this.isbn = null;
        this.year = 0;
        this.price = 0;
    }


    public Book(String title, String author, String isbn, int year, double price, Category category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public int getYear() {
        return year;
    }


    public void setYear(int year) {
        this.year = year;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


     public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        if (this.category != null)
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", year=" + year
                + ", price=" + price + ", category=" + this.getCategory() + "]";
        else
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", year=" + year
                + ", price=" + price + ", category=" + "]";
        }
        
    }

