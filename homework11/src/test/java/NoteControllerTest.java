import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.innopolis.App;
import ru.innopolis.controller.NoteController;
import ru.innopolis.entity.Note;
import ru.innopolis.service.NoteService;

import java.util.List;


@ActiveProfiles("debug")
@SpringBootTest(classes = {App.class})
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    void createNoteTest() throws Exception {
        Mockito.when(noteService.createNote(1,"TestTheme1","TestText1")).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(noteForCreate))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findByIdNoteTest() throws Exception {
        Mockito.when(noteService.findByIdNote(5)).thenReturn(new Note(5,"","5","5"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/find/{id}", 5))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5));
    }

    @Test
    void findAllNoteTest() throws Exception {
        Mockito.when(noteService.findAllNote()).thenReturn(listNote);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/findall"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void updateNoteTest() throws Exception {
        Mockito.when(noteService.updateNote(1,"TestTheme1","TestText1")).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(noteForUpdate))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteByIdNoteTest() throws Exception {
        Mockito.when(noteService.deleteByIdNote(1)).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(1));
    }

    @Test
    void deleteAllNoteTest() throws Exception {
        Mockito.when(noteService.deleteAllNote()).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deleteall"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(1));
    }


    private List<Note> listNote = List.of(
            new Note(1,"2025-02-04 12:00:00","TestTheme1","TestText1"),
            new Note(2,"2025-02-04 12:10:00","TestTheme2","TestText2"),
            new Note(3,"2025-02-04 12:20:00","TestTheme3","TestText3")
    );

    private String noteForCreate = """
                {
                    "id": 1,
                    "theme": "Тема текста",
                    "textnote": "Текст"
                }
            """;

    private String noteForUpdate = """
                {
                    "theme": "Тема текста 2",
                    "textnote": "Текст 2"
                }
            """;

}
