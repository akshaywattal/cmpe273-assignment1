package edu.sjsu.cmpe.library.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Review;

@JsonPropertyOrder(alphabetic = true)
public class ReviewsDto extends LinksDto{
    private List<Review> review;

    public ReviewsDto(List<Review> review) {
    	super();
    	this.review = review;
        }
    

    /**
     * @return the review
     */
    public List<Review> getReview() {
	return review;
    }

    /**
     * @param review
     *            the review to set
     */
    public void setReview(List<Review> review) {
	this.review = review;
    }
}
