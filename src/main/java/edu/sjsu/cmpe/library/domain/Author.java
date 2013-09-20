package edu.sjsu.cmpe.library.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Author {
    public long id;
    
    @NotEmpty
    private String name;

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
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }
  
}
