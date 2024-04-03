package com.nocaffeine.ssgclone.review.application;

import com.nocaffeine.ssgclone.review.dto.request.ReviewAddRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewRemoveRequestDto;

public interface ReviewService {

    void addReview(ReviewAddRequestDto reviewAddRequestDto, String memberUuid);
    void removeReview(ReviewRemoveRequestDto reviewRemoveRequestDto, String memberUuid);
    void findWritableReviews(String memberUuid);
}