package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Note;
import ru.innopolis.repository.NoteRepository;

import java.util.List;


@Repository
public class NoteRepositoryImpl implements NoteRepository {

    private final JdbcTemplate jdbcTemplate;

    public NoteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CREATE = """
                INSERT INTO note (id, notedate, theme, textnote)
                VALUES (?, ?::timestamp, ?, ?)
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM note
                WHERE id = ?
            """;
    private static final String FIND_ALL = """
                SELECT
                    *
                FROM note
            """;
    private static final String UPDATE = """
                UPDATE note
                SET theme = ?,
                    textnote = ?
                WHERE id = ?
            """;
    private static final String DELETE_BY_ID = """
                DELETE FROM note
                WHERE id = ?;
            """;
    private static final String DELETE_ALL = """
                DELETE FROM note;
            """;


    @Override
    public int create(Integer id, String noteDate, String theme, String textnote) {
        return jdbcTemplate.update(CREATE, id, noteDate, theme, textnote);
    }

    @Override
    public Note findById(Integer id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, noteRowMapper, id);
    }

    @Override
    public List<Note> findAll() {
        return jdbcTemplate.query(FIND_ALL, noteRowMapper);
    }

    @Override
    public int update(Integer id, String theme, String textnote) {
        return jdbcTemplate.update(UPDATE, theme, textnote, id);
    }

    @Override
    public int deleteByID(Integer id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update(DELETE_ALL);
    }

    private static final RowMapper<Note> noteRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String noteDate = row.getString("noteDate");
        String theme = row.getString("theme");
        String textnote = row.getString("textnote");

        return new Note(id, noteDate, theme, textnote);
    };

}
