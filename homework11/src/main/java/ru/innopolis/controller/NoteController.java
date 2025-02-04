package ru.innopolis.controller;

import org.springframework.web.bind.annotation.*;
import ru.innopolis.entity.Note;
import ru.innopolis.service.NoteService;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // http://localhost:8081/api/v1/create
    @PostMapping("/create")
    public int createNone(@RequestBody Note note) {
        return noteService.createNote(note.getId(), note.getTheme(), note.getTextnote());
    }

    // http://localhost:8081/api/v1/find/1
    @GetMapping("/find/{id}")
    public Note findByIdNote(@PathVariable Integer id) {
        return noteService.findByIdNote(id);
    }

    // http://localhost:8081/api/v1/findall
    @GetMapping("/findall")
    public List<Note> findAllNote() {
        return noteService.findAllNote();
    }

    // http://localhost:8081/api/v1/update/1
    @PutMapping("/update/{id}")
    public int updateNote(@PathVariable Integer id, @RequestBody Note note) {
        return noteService.updateNote(id, note.getTheme(), note.getTextnote());
    }

    // http://localhost:8081/api/v1/delete/1
    @DeleteMapping("/delete/{id}")
    public int deleteByIdNote(@PathVariable Integer id) {
        return noteService.deleteByIdNote(id);
    }

    // http://localhost:8081/api/v1/deleteall
    @DeleteMapping("/deleteall")
    public int deleteAllNote() {
        return noteService.deleteAllNote();
    }

}
