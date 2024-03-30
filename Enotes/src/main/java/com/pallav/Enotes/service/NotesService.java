package com.pallav.Enotes.service;

import java.util.List;

import com.pallav.Enotes.entity.Notes;
import com.pallav.Enotes.entity.User;

public interface NotesService {

	public Notes saveNotes(Notes note);
	
	public Notes getNotesById(int id);
	
	public List<Notes> getNotesByUser(User user);
	
	public Notes updateNotes(Notes note);
	
	public boolean deleteNote(int id);
	
}
