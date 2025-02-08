package ru.innopolis.repository;

import ru.innopolis.entity.Note;

import java.util.List;


public interface NoteRepository {

    int create(Integer id, String noteDate, String theme, String textnote);
    Note findById(Integer id);
    List<Note> findAll();
    int update(Integer id, String theme, String textnote);
    int deleteByID(Integer id);
    int deleteAll();

}
