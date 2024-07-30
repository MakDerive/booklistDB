package booklist.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
	private int id;
	@NotEmpty
	@Size(min=3,max=30,message="Name too short or too long")
	private String name;
	@NotEmpty
	@Size(min=3,max=30,message="Name too short or too long")
	private String author;
	@Min(value=1,message = "not 0 or less")
	private int price;
	
	public Book() {
		
	}
	
	
	public Book(int id, String name, String author, int price) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
