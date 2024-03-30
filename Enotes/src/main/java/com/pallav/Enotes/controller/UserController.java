package com.pallav.Enotes.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pallav.Enotes.entity.Notes;
import com.pallav.Enotes.entity.User;
import com.pallav.Enotes.repo.UserRepo;
import com.pallav.Enotes.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private NotesService notesService;
    
    @ModelAttribute
    public User getUser(Model m ,Principal p)
    {
    	String email=p.getName();
        User user = userRepo.findByEmail(email);
        m.addAttribute("user",user);
        return user;
    }
	
	@GetMapping("/add_note")
	public String add_note()
	{
		return "add_note";
	}
	@GetMapping("/edit_note/{id}")
	public String edit_note(@PathVariable int id ,Model m)
	{
		Notes notes = notesService.getNotesById(id);
		m.addAttribute("n", notes);
		return "edit_note";
	}
	
	@GetMapping("/view_note")
	public String view(Model m ,Principal p)
	{
		User user = getUser(m, p);
		 List<Notes> notes= notesService.getNotesByUser(user);
		 m.addAttribute("notes", notes);
		return "view";
	}
	
	@PostMapping("/saveNotes")
	public String savenotes(@ModelAttribute Notes note,HttpSession session,Model m ,Principal p)
	{
		note.setDate(LocalDate.now());
		note.setUser(getUser(m, p));
		Notes newNote=notesService.saveNotes(note);
		if(newNote==null)
			session.setAttribute("msg", "Notes Not Saved");
		else
			session.setAttribute("msg", "Notes saved");
		
		return "redirect:/user/view_note";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, HttpSession session)
	{
		boolean deleteNote = notesService.deleteNote(id);
		if(!deleteNote)
			session.setAttribute("msg", "Notes not Deleted");
		else
			session.setAttribute("msg", "Notes Deleted Sucessfully");
		return "redirect:/user/view_note";
	}
	@PostMapping("/update")
	public String updatenotes(@ModelAttribute Notes note,HttpSession session,Model m ,Principal p)
	{
		note.setDate(LocalDate.now());
		note.setUser(getUser(m, p));
		Notes newNote=notesService.saveNotes(note);
		if(newNote==null)
			session.setAttribute("msg", "Notes Not Saved");
		else
			session.setAttribute("msg", "Updated Sucessfully");
		
		return "redirect:/user/view_note";
	}
}
