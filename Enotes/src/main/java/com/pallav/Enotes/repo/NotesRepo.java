package com.pallav.Enotes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pallav.Enotes.entity.Notes;
import com.pallav.Enotes.entity.User;

public interface NotesRepo extends JpaRepository<Notes,Integer> {

	public List<Notes> findByUser(User user);
}
