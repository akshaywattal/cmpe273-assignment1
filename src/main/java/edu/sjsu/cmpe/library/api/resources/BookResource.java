package edu.sjsu.cmpe.library.api.resources;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	
	private static long book_id=1;
	private static long author_id=1;
	private static long review_id=1;
	private static HashMap<Long, Book> new_book_entry = new HashMap<Long,Book>();
	
	@POST
	@Timed(name = "create-book")
	public Response createBook(Book book) {
		book.setIsbn(book_id); 
		new_book_entry.put(book_id, book);
		book_id++;

		for (int author=0;author<book.getAuthors().length;author++)
		{
			book.getAuthors()[author].id=author_id;
			author_id++;
			
		}
		
		BookDto bookResponse = new BookDto();
		bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn() + "/reviews", "POST"));
		
		return Response.status(201).entity(bookResponse.getLinks()).build();
	}
    
	@GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto viewBook(@PathParam("isbn") long isbn) {
		
		Book retrieveBook=new_book_entry.get(isbn);
		
		BookDto bookResponse = new BookDto(retrieveBook);
		bookResponse.addLink(new LinkDto("view-book", "/books/" + retrieveBook.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + retrieveBook.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + retrieveBook.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review","/books/" + retrieveBook.getIsbn() + "/reviews", "POST"));
		if (retrieveBook.getReviews().size() !=0 ){
		bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + retrieveBook.getIsbn() + "/reviews", "GET"));
		}
		
	return bookResponse;
    }
	
	@DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response deleteBook(@PathParam("isbn") long isbn) {
		
		new_book_entry.remove(isbn);
		
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("create-book", "/books", "POST"));
		
	return Response.ok(links).build();
    }
	
	
	@PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBook(@PathParam("isbn") long isbn, @QueryParam("status") String status) {
		
		Book retrieveBook=new_book_entry.get(isbn);
		retrieveBook.setStatus(status);
		
		BookDto bookResponse = new BookDto();
		bookResponse.addLink(new LinkDto("view-book", "/books/" + retrieveBook.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + retrieveBook.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + retrieveBook.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review","/books/" + retrieveBook.getIsbn() + "/reviews", "POST"));
		if (retrieveBook.getReviews().size() !=0 ){
			bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + retrieveBook.getIsbn() + "/reviews", "GET"));
			}
		
	return Response.ok().entity(bookResponse.getLinks()).build();
    }
	
	@POST
    @Path("/{isbn}/reviews")
    @Timed(name = "create-review")
    public Response createReview(@PathParam("isbn") long isbn, Review reviews) {
		
		Book retrieveBook = new_book_entry.get(isbn);
		
		reviews.setID(review_id);
		retrieveBook.getReviews().add(reviews);
		review_id++;
		
		ReviewDto reviewResponse = new ReviewDto();
		reviewResponse.addLink(new LinkDto("view-review", "/books/" + retrieveBook.getIsbn() + "/reviews/" + reviews.getID(), "GET"));
		
	return Response.status(201).entity(reviewResponse.getLinks()).build();
    }
	
	@GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-review")
    public ReviewDto viewReview(@PathParam("isbn") long isbn, @PathParam("id") long id) {
		int i=0;
		Book retrieveBook = new_book_entry.get(isbn);
		while (retrieveBook.getoneReview(i).getID()!=id)
		{
			i++;
		}
		ReviewDto reviewResponse = new ReviewDto(retrieveBook.getoneReview(i));
		reviewResponse.addLink(new LinkDto("view-review", "/books/" + retrieveBook.getIsbn() + "/reviews/" + retrieveBook.getoneReview(i).getID(), "GET"));
		
	return reviewResponse;
    }
	
	@GET
    @Path("/{isbn}/reviews")
    @Timed(name = "view-all-reviews")
    public ReviewsDto viewAllReviews(@PathParam("isbn") long isbn) {
		
		Book retrieveBook = new_book_entry.get(isbn);
		ReviewsDto reviewResponse = new ReviewsDto(retrieveBook.getReviews());
				
	return reviewResponse;
    }
	
	@GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-author")
    public Response viewAuthor(@PathParam("isbn") long isbn, @PathParam("id") long id) {
		int i=0;
		Book retrieveBook = new_book_entry.get(isbn);
		while (retrieveBook.getoneAuthors((int)i).id!=id)
		{
			i++;
		}
		AuthorDto authorResponse = new AuthorDto(retrieveBook.getoneAuthors((int)i));
		authorResponse.addLink(new LinkDto("view-author", "/books/" + retrieveBook.getIsbn() + "/authors/" + retrieveBook.getoneAuthors((int)i).id, "GET"));
		
	return Response.ok(authorResponse).build();
    }
	
	@GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-all-authors")
    public AuthorsDto viewAllAuthors(@PathParam("isbn") long isbn) {
		
		Book retrieveBook = new_book_entry.get(isbn);
		AuthorsDto authorResponse = new AuthorsDto(retrieveBook.getAuthors());
				
	return authorResponse;
    }
}