package com.nocaffeine.ssgclone.review.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.order.domain.OrderProduct;
import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.infrastructure.OrderProductRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import com.nocaffeine.ssgclone.product.domain.Image;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.infrastructure.ImageRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import com.nocaffeine.ssgclone.review.domain.Review;
import com.nocaffeine.ssgclone.review.domain.ReviewImage;
import com.nocaffeine.ssgclone.review.dto.request.ReviewAddRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewRemoveRequestDto;
import com.nocaffeine.ssgclone.review.infrastructure.ReviewImageRepository;
import com.nocaffeine.ssgclone.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ImageRepository imageRepository;


    /**
     * 리뷰 등록
     */
    @Override
    @Transactional
    public void addReview(ReviewAddRequestDto reviewAddRequestDto, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Product product = productRepository.findById(reviewAddRequestDto.getProductId())
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        Orders order = orderRepository.findById(reviewAddRequestDto.getOrderId())
                .orElseThrow(() -> new BaseException(NO_EXIST_ORDER));

        Review review = reviewRepository.save(Review.builder()
                .product(product)
                .memberUuid(member.getUuid())
                .content(reviewAddRequestDto.getContent())
                .rate(reviewAddRequestDto.getRate())
                .order(order)
                .build());

        for(String imageUrl : reviewAddRequestDto.getImageUrl()) {
            Image image = imageRepository.save(Image.builder()
                    .url(imageUrl)
                    .build());

            reviewImageRepository.save(ReviewImage.builder()
                    .review(review)
                    .image(image)
                    .build());
        }
    }

    /**
     * 리뷰 삭제
     */
    @Override
    @Transactional
    public void removeReview(ReviewRemoveRequestDto reviewRemoveRequestDto, String memberUuid) {
        Review review = reviewRepository.findById(reviewRemoveRequestDto.getReviewId())
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        List<ReviewImage> reviewImage = reviewImageRepository.findByReview(review);
        reviewImageRepository.deleteAll(reviewImage);


        reviewRepository.delete(review);
    }

}