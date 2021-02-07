package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotes(int userId){
        return noteMapper.getNotes(userId);
    }

    public int addNote(Note note, int userId){
        note.setUserId(userId);
        return noteMapper.addNote(note);
    }

    public int updateNote(Note note){
        return noteMapper.updateNote(note);
    }

    public int deleteNote(int noteId){
        return noteMapper.delete(noteId);
    }
}
