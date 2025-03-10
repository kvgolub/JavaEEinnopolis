package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.StudentCourse;

import java.util.List;


@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long>,
        JpaSpecificationExecutor<StudentCourse> {

    List<StudentCourse> findStudentCourseByStudentId(Long studentId);
    List<StudentCourse> findStudentCourseByCourseId(Long courseId);

    @Query(nativeQuery = true, value = """
                WITH
                    queryCount as(
                        SELECT
                            count(*) over(PARTITION BY student_id) AS studentCount,
                            *
                        FROM students_courses
                    )
                SELECT
                    id,
                    student_id,
                    course_id
                FROM queryCount
                WHERE queryCount.studentCount >= :courseCount
            """)
    List<StudentCourse> findAllStudentsTwoOrMoreCourses(@Param("courseCount") Long courseCount);

    @Query(nativeQuery = true, value = """
                SELECT
                    sc.*
                FROM students s
                INNER JOIN students_courses sc ON sc.student_id = s.id
                INNER JOIN courses c ON c.id = sc.course_id
                WHERE s."name" ILIKE concat('%', :studentName, '%')
                    AND c."name" ILIKE '%' || :courseName || '%'
            """)
    List<StudentCourse> findStudentByNameAndCourseByName(String studentName, String courseName);

    @Query(nativeQuery = true, value = """
                WITH
                	queryCount as(
                		SELECT
                			count(*) over(PARTITION BY student_id) AS studentCount,
                			*
                		FROM students_courses
                	)
                SELECT
                	qc.id,
                	qc.student_id,
                	qc.course_id
                FROM queryCount qc
                INNER JOIN students s ON s.id = qc.student_id
                WHERE qc.studentCount >= :courseCount
                	AND s.age >= :age
            """)
    List<StudentCourse> findAllStudentsCertainAgeTwoOrMoreCourses(Integer age, Long courseCount);

}
