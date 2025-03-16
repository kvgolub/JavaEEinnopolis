package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Review;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findReviewsByStudentIdAndCourseId(Long studentId, Long courseId);

}
