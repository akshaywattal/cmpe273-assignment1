package edu.sjsu.cmpe.library.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class Review {
	private long id;
	
	@NotNull
	@Min(1)
	private int rating; 
	
	@NotEmpty
	private String comment;

    /**
     * @return the id
     */
    public long getID() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setID(long id) {
	this.id = id;
    }

    /**
     * @return the rating
     */
    public int getRating() {
	return rating;
    }

    /**
     * @param rating
     *            the rating to set
     */
    public void setRating(int rating) {
	this.rating = rating;
    }
    
    /**
     * @return the rating
     */
    public String getComment() {
	return comment;
    }

    /**
     * @param rating
     *            the rating to set
     */
    public void setComment(String comment) {
	this.comment = comment;
    }
  
}
