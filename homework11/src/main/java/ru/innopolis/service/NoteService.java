package ru.innopolis.service;

import org.springframework.stereotype.Service;
import ru.innopolis.entity.Note;
import ru.innopolis.repository.impl.NoteRepositoryImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class NoteService {

    private final NoteRepositoryImpl note;

    public NoteService(NoteRepositoryImpl note) {
        this.note = note;
    }

    public int createNote(Integer id, String theme, String textnote) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = formatter.format(date);

        return note.create(id, strDate, theme, textnote);
    }

    public Note findByIdNote(Integer id) {
        return note.findById(id);
    }

    public List<Note> findAllNote() {
        return note.findAll();
    }

    public int updateNote(Integer id, String theme, String textnote) {
        return note.update(id, theme, textnote);
    }

    public int deleteByIdNote(Integer id) {
        return note.deleteByID(id);
    }

    public int deleteAllNote() {
        return note.deleteAll();
    }

}
