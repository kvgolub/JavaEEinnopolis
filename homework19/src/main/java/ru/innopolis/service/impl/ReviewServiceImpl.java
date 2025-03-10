package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
import ru.innopolis.dto.review.FullDataReviewResponse;
import ru.innopolis.dto.review.ReviewRequest;
import ru.innopolis.entity.Course;
import ru.innopolis.entity.Review;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.CourseRepository;
import ru.innopolis.repository.ReviewRepository;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, ReviewRepository reviewRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public FullDataReviewResponse createReview(ReviewRequest reviewRequest) {
        try {
            Student student = studentRepository.findById(reviewRequest.getStudentId()).orElseThrow();
            Course course = courseRepository.findById(reviewRequest.getCoursesId()).orElseThrow();
            Review review = reviewRepository.findReviewsByStudentIdAndCourseId(reviewRequest.getStudentId(), reviewRequest.getCoursesId());

            if(review == null) {
                reviewRepository.save(
                        new Review(
                                null,
                                reviewRequest.getStudentId(),
                                reviewRequest.getCoursesId(),
                                reviewRequest.getText()
                        )
                );
            }

            return new FullDataReviewResponse(
                    student.getId(),
                    student.getSurname(),
                    student.getName(),
                    student.getPatronymic(),
                    student.getAge(),
                    student.getEmail(),
                    course.getId(),
                    course.getName(),
                    course.getDate(),
                    course.getActive(),
                    reviewRequest.getText()
            );
        } catch (Exception e) {
            return null;
        }
    }

}
