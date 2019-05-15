package org.fasttrackit.onlineshopapi.service;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.domain.Review;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.persistence.ReviewRepository;
import org.fasttrackit.onlineshopapi.transfer.product.review.CreateReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServices {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    @Autowired
    public ReviewServices(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    public Review createReview(CreateReviewRequest request) throws ResourceNotFoundException {
        Product product = productService.getProduct(request.getProductId());

        Review review = new Review();
        review.setContent(request.getContent());
        review.setProduct(product);

        return reviewRepository.save(review);
    }
}
