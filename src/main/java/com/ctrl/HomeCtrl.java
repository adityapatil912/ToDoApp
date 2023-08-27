package com.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.TodoDAO;
import com.entities.Todo;

@Controller
public class HomeCtrl {

	@Autowired
	ServletContext context;

	@Autowired
	TodoDAO todoDAO;

	@RequestMapping("/home")
	public String Home(Model m) {
		String str = "home";
		m.addAttribute("page", str);

		List<Todo> list = this.todoDAO.getAll();
		m.addAttribute("todos", list);
		return "home";
	}

	@RequestMapping("/add")
	public String addTodo(Model m) {
		Todo t = new Todo();
		m.addAttribute("page", "add");
		m.addAttribute("todo", t);
		return "home";
	}

	@RequestMapping(value = "/saveTodo", method = RequestMethod.POST)
	public String saveTodo(@ModelAttribute("todo") Todo t, Model m) {
		t.setTodoDate(new Date());
		this.todoDAO.save(t);
		m.addAttribute("msg", "Successfully added...");
		return "home";
	}

	@RequestMapping("/update/{id}")
	public String updateTodo(@PathVariable("id") int id, Model m) {
		Todo todo = this.todoDAO.getById(id);
		m.addAttribute("page", "update");
		m.addAttribute("todo", todo);
		return "updateForm";
	}

	@RequestMapping(value = "/updateTodo", method = RequestMethod.POST)
	public String updateTodo(@ModelAttribute("todo") Todo t, Model m) {
		t.setTodoDate(new Date());
		this.todoDAO.update(t);
		m.addAttribute("msg", "Successfully updated...");
		return "redirect:/home";
	}

	@RequestMapping("/delete/{id}")
	public String deleteTodo(@PathVariable("id") int id, Model m) {
		Todo todo = this.todoDAO.getById(id);
		this.todoDAO.delete(todo);
		m.addAttribute("msg", "Successfully deleted...");
		return "redirect:/home";
	}

}
