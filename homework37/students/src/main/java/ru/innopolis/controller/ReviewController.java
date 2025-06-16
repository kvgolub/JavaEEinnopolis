package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.dto.review.FullDataReviewResponse;
import ru.innopolis.dto.review.ReviewRequest;


@RequestMapping("/api/v1/review")
public interface ReviewController {

    @PostMapping
    ResponseEntity<FullDataReviewResponse> createReview(@Valid @RequestBody ReviewRequest reviewRequest);
}
