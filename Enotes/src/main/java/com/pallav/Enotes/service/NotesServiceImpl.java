package com.pallav.Enotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pallav.Enotes.entity.Notes;
import com.pallav.Enotes.entity.User;
import com.pallav.Enotes.repo.NotesRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class NotesServiceImpl implements NotesService {
    @Autowired
    private NotesRepo notesRepo;
	
	@Override
	public Notes saveNotes(Notes note) {
		Notes newNote= notesRepo.save(note);
		return newNote;
	}

	@Override
	public Notes getNotesById(int id) {
		
		return notesRepo.findById(id).get();
	}

	@Override
	public List<Notes> getNotesByUser(User user) {
	
		return notesRepo.findByUser(user);
	}

	@Override
	public boolean deleteNote(int id) {
		Notes note =notesRepo.findById(id).get();
		if(note!=null)
		{
			notesRepo.delete(note);
			return true;
		}
		return false;
	}

	@Override
	public Notes updateNotes(Notes note) {
	
		return notesRepo.save(note);
	}


	public void removeSession()
	{

		 HttpSession session= ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).
		  getRequest().getSession();
	 session.removeAttribute("msg");
	}
}
