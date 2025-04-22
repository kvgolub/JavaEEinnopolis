package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.ReviewController;
import ru.innopolis.dto.review.FullDataReviewResponse;
import ru.innopolis.dto.review.ReviewRequest;
import ru.innopolis.service.ReviewService;


@RestController
@RequiredArgsConstructor
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;

    @Override
    public ResponseEntity<FullDataReviewResponse> createReview(ReviewRequest reviewRequest) {
        FullDataReviewResponse fullDataReviewResponse = reviewService.createReview(reviewRequest);

        if (fullDataReviewResponse != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(fullDataReviewResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
