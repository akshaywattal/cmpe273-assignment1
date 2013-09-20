package edu.sjsu.cmpe.library.domain;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "isbn", "title", "publication-date", "language", "num-pages", "status", "reviews", "authors"})
public class Book {
	
	@JsonProperty
	private long isbn;
	
	@NotEmpty
	@JsonProperty
    private String title;
    
	@NotEmpty
	@JsonProperty("publication-date")
    private String publicationDate;
    
	@JsonProperty
    private String language;	
    
	@JsonProperty("num-pages")
    private int noOfPages; 				
    
	@JsonProperty
    private String status="available"; 	
    
	@JsonProperty
    private List<Review> reviews = new ArrayList<Review>();
    
	@JsonProperty
    private Author[] authors;
    
    /**
     * @return the isbn
     */
    public long getIsbn() {
	return this.isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return this.title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    /**
     * @return the publicationDate
     */
    public String getPublicationDate() {
	return this.publicationDate;
    }
    
    /**
     * @param publicationDate
     *            the publicationDate to set
     */
    
    public void setPublicationDate(String publicationDate) {
	this.publicationDate = publicationDate;
    }
    
    /**
     * @return the language
     */
    public String getLanguage() {
	return this.language;
    }
    
    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(String language) {
	this.language = language;
    }
   
    /**
     * @return the noOfPages
     */
    public int getNoOfPages() {
	return this.noOfPages;
    }
    
    /**
     * @param language
     *            the language to set
     */
    public void setNoOfPages(int noOfPages) {
	this.noOfPages = noOfPages;
    }
   
    /**
     * @return the status
     */
    public String getStatus() {
	return this.status;
    }
    
    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
	this.status = status;
    }

    /**
     * @return the authors
     */
    public Author[] getAuthors() {
		return this.authors;
	}

    /**
     * @param authors
     *            the authors to set
     */
    public void setAuthors(Author[] authors) {
		this.authors = authors;
	}

    /**
     * @return the reviews
     */
    public List<Review> getReviews() {
		return this.reviews;
	}
    
    /**
     * @param reviews
     *            the reviews to set
     */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Author getoneAuthors(int id) {
		return this.authors[id];
		
	}
	
	public Review getoneReview(int id) {
		return this.reviews.get(id);
		
	}

}
