package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;

@JsonPropertyOrder(alphabetic = true)
public class AuthorsDto extends LinksDto {
    private Author[] author;

    /**
     * @param book
     */
    public AuthorsDto(Author[] author) {
    	super();
    	this.author = author;
        }

	/**
     * @return the author
     */
    public Author[] getAuthor() {
	return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(Author[] author) {
	this.author = author;
    }

}
