package com.backbase.goldensample.review.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.backbase.goldensample.review.persistence.ReviewEntity;
import com.backbase.reviews.api.service.v1.model.Review;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class MapperTests {

    private final com.backbase.goldensample.review.mapper.ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);
    ;

    @Test
    void mapperTests() {

        assertNotNull(mapper);

        Review review = new Review().productId(1L).reviewId(1L).author("author").content("long content")
            .subject("subject");

        ReviewEntity entity = mapper.apiToEntity(review);

        assertAll(() -> assertEquals(review.getProductId(), entity.getProductId(), "Product ID is different"),
            () -> assertEquals(review.getReviewId(), entity.getId(), "Review ID is different"),
            () -> assertEquals(review.getAuthor(), entity.getAuthor(), "Author is different"),
            () -> assertEquals(review.getSubject(), entity.getSubject(), "Subject is different"),
            () -> assertEquals(review.getContent(), entity.getContent(), "Content is different"));

        Review api2 = mapper.entityToApi(entity);

        assertAll(() -> assertEquals(review.getProductId(), api2.getProductId(), "Product ID is different"),
            () -> assertEquals(review.getReviewId(), api2.getReviewId(), "Review ID is different"),
            () -> assertEquals(review.getAuthor(), api2.getAuthor(), "Author is different"),
            () -> assertEquals(review.getSubject(), api2.getSubject(), "Subject is different"),
            () -> assertEquals(review.getContent(), api2.getContent(), "Content is different"));
    }

    @Test
    void mapperListTests() {

        assertNotNull(mapper);

        Review review =
            new Review().productId(1L).reviewId(1L).author("author")
                .content("long content").subject("subject");
        List<Review> apiList = Collections.singletonList(review);

        List<ReviewEntity> entityList = mapper.apiListToEntityList(apiList);
        assertEquals(apiList.size(), entityList.size());

        ReviewEntity entity = entityList.get(0);

        assertAll(() -> assertEquals(review.getProductId(), entity.getProductId(), "Product ID is different"),
            () -> assertEquals(review.getReviewId(), entity.getId(), "Review ID is different"),
            () -> assertEquals(review.getAuthor(), entity.getAuthor(), "Author is different"),
            () -> assertEquals(review.getSubject(), entity.getSubject(), "Subject is different"),
            () -> assertEquals(review.getContent(), entity.getContent(), "Content is different"));

        List<Review> api2List = mapper.entityListToApiList(entityList);
        assertEquals(apiList.size(), api2List.size());

        Review api2 = api2List.get(0);

        assertAll(() -> assertEquals(review.getProductId(), api2.getProductId(), "Product ID is different"),
            () -> assertEquals(review.getReviewId(), api2.getReviewId(), "Review ID is different"),
            () -> assertEquals(review.getAuthor(), api2.getAuthor(), "Author is different"),
            () -> assertEquals(review.getSubject(), api2.getSubject(), "Subject is different"),
            () -> assertEquals(review.getContent(), api2.getContent(), "Content is different"));
    }
}
