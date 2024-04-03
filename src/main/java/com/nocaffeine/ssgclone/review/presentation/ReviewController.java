package com.nocaffeine.ssgclone.review.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.review.application.ReviewService;
import com.nocaffeine.ssgclone.review.dto.request.ReviewAddRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewRemoveRequestDto;
import com.nocaffeine.ssgclone.review.vo.request.ReviewAddRequestVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰")
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public CommonResponse<String> reviewAdd(@RequestBody ReviewAddRequestVo reviewAddRequestVo) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        reviewService.addReview(ReviewAddRequestDto.voToDto(reviewAddRequestVo), memberUuid);
        return CommonResponse.success("리뷰 등록 성공");
    }

    @DeleteMapping
    public CommonResponse<String> reviewRemove(@RequestBody ReviewRemoveRequestDto reviewRemoveRequestDto) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        reviewService.removeReview(reviewRemoveRequestDto, memberUuid);
        return CommonResponse.success("리뷰 삭제 성공");
    }

}