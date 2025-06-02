package ru.innopolis.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import ru.innopolis.entity.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-postgres.properties")
//@TestPropertySource(locations = "classpath:application-h2.properties")
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void initTask(){
        Task task = new Task(
                null,
                "Задача № 1",
                "Выполнить домашнее задание №1",
                LocalDate.of(2025, 5, 1)
        );
        testEntityManager.persistAndFlush(task);

//        taskRepository.save(task);
    }


    @ParameterizedTest
    @MethodSource("datesForTask")
    void findTasksBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Task> taskList = taskRepository.findTasksByCreatedAtBetween(startDate, endDate);

        assertThat("Тестирование \"Найти задачи в промежутке дат\"", taskList.size(), equalTo(1));
    }


    private static Stream<Arguments> datesForTask() {
        return Stream.of(
                Arguments.of(
                    LocalDate.of(2025, 5, 1),
                    LocalDate.of(2025, 5, 2)
                )
        );
    }

}