package ru.innopolis.service;

import ru.innopolis.dto.review.FullDataReviewResponse;
import ru.innopolis.dto.review.ReviewRequest;


public interface ReviewService {

    FullDataReviewResponse createReview(ReviewRequest reviewRequest);

}
