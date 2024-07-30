package booklist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import booklist.models.Book;

@Component
public class BooklistDao {
	
	final JdbcTemplate jdbcTemplate;
	@Autowired
	public BooklistDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Book> index(){
		return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
	}
	
	public Book show(int id) {

    	return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",new Object[] {id}, new BookMapper())
    			.stream().findAny().orElse(null);
        
    }
	
	public void save(Book newBook) {
		jdbcTemplate.update("INSERT INTO Book (name,author,price) VALUES(?,?,?)",newBook.getName(),newBook.getAuthor(),newBook.getPrice());
	}
	
	public void update(Book updatedBook,int id ) {
		jdbcTemplate.update("UPDATE Book SET name=?,author=?,price=? WHERE id=?",updatedBook.getName(),updatedBook.getAuthor(),updatedBook.getPrice(),id);
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Book WHERE id=?",id);
	}
	

	
	
	
}
