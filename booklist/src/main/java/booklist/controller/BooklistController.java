package booklist.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import booklist.dao.BooklistDao;
import booklist.models.Book;

@Controller
@RequestMapping("/booklist")
public class BooklistController {
	
	private BooklistDao booklistDao;
	
	@Autowired
	public BooklistController(BooklistDao booklistDao) {
		this.booklistDao = booklistDao;
	}
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("books",booklistDao.index());
		return "booklist/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("book",booklistDao.show(id));
		return "booklist/show";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("book",booklistDao.show(id));
		return "/booklist/edit";
	}
	@PostMapping
	public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			return "redirect:/booklist";
		}
		booklistDao.save(book);
		return "redirect:/booklist";
	}
	
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult,@PathVariable("id") int id ) {
		if(bindingResult.hasErrors()) {
			return "booklist/edit";
		}
		booklistDao.update(book,id);
		return "redirect:/booklist";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		booklistDao.delete(id);
		return "redirect:/booklist";
	}
}
