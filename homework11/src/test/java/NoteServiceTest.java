import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import ru.innopolis.App;
import ru.innopolis.repository.NoteRepository;
import ru.innopolis.repository.impl.NoteRepositoryImpl;
import ru.innopolis.service.NoteService;


@ActiveProfiles("debug")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {NoteService.class, NoteRepositoryImpl.class})
//@SpringBootTest(classes = {App.class})
@AutoConfigureJdbc
public class NoteServiceTest {



    @Autowired
    private NoteService noteService;

    @Order(1)
    @Test
    void createNoteTest() {
        var response = noteService.createNote(1, "TestTheme", "TestText");

        Assertions.assertEquals(1, response);
    }

    @Order(2)
    @Test
    void findByIdNoteTest() {
        var response = noteService.findByIdNote(1);

        Assertions.assertEquals(1, response.getId());
    }

    @Order(3)
    @Test
    void findAllNoteTest() {
        var response = (long) noteService.findAllNote().size();

        Assertions.assertEquals(1, response);
    }

    @Order(4)
    @Test
    void updateNoteTest() {
        var response = noteService.updateNote(1,"TestTheme 2", "TestText 2");

        Assertions.assertEquals(1, response);
    }

    @Order(5)
    @Test
    void deleteByIdNoteTest() {
        var response = noteService.deleteByIdNote(1);

        Assertions.assertEquals(1, response);
    }

    @Order(6)
    @Test
    void deleteAllNoteTest() {
        noteService.createNote(2, "TestTheme2", "TestText2");
        var response = noteService.deleteAllNote();

        Assertions.assertEquals(1, response);
    }

}
