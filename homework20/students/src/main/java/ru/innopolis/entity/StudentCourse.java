package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "students_courses", schema = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    @PrimaryKeyJoinColumn
    private Long studentId;

    @Column(name = "course_id")
    @PrimaryKeyJoinColumn
    private Long courseId;

}
