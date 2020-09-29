// --== CS400 File Header Information ==--
// Name: Timothy Eric Doughery
// Email: doughery@wisc.edu
// Team: CB
// TA: Yeping Wang
// Lecturer: Florian Heimerl
// Notes to Grader: <none>

/*
 * @author - Timothy Eric Doughery
 * This class is a database that contains Book objects by using the HashTableMap class. Librarians
 * can add or remove books, while students can search for, check out, or check in a book,
 */
public class LibraryDB {
	HashTableMap<String, Book> foo = null;
	
	/**
	 * Class constructor
	 * @param none
	 */
	public LibraryDB() {
		//loads a number of default books to the library
		foo = new HashTableMap<String, Book>();
		Book a = new Book("Frankenstein", "Mary Shelley", "Fiction", 111, true);
		Book b = new Book("King Lear", "William Shakespeare", "Drama", 222, true);
		Book c = new Book("The Scarlet Letter", "Nathaniel Hawthorne", "Historical Fiction", 333, true);
		Book d = new Book("Heart of Darkness", "Joseph Conrad", "Novel", 444, true);
		Book e = new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 555, true);
		Book[] bar = {a, b, c, d, e};
		for (Book i: bar) {
			foo.put(i.getTitle(), i);
		}
	}
	
	/** 
	 * Adds a book to the library. Only accessible by librarians.
	 * 
	 * @param {String} key - The books' title.
	 * @param {String} author - Author of the book.
	 * @param {String} genre - The book's genre.
	 * @param {int} isbn - The book's ISBN value.
	 * 
	 * @return a boolean indicating if the book has successfully been added.
	 */
	public boolean addBook(String key, String author, String genre, int isbn) {
		Book temp = new Book (key, author, genre, isbn, true);
		return foo.put(key, temp);
	}
	
	/**
	 * Removes a book from the library. Only accessible by librarians.
	 * 
	 * @param {String} key - Title of the book to be removed.
	 * 
	 * @return a boolean indicating if the book has successfully been removed.
	 */
	public boolean removeBook(String key) {
		if (foo.remove(key) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Gets a Book object from the library.
	 * 
	 * @param {String} key - The book's title.
	 * 
	 * @return the book if it can be found in the library, null otherwise.
	 */
	public Book getBook(String key) { 
		Book temp = null;
		
		try {
			temp = foo.get(key);	
		} catch (Exception e) { //if the book doesn't exist in the library, it can throw an exception.
			//Nothing is done here, so it doesn't interfere with the UI
		}
		
		return temp;
	}

	/**
	 * Gets the size of the library.
	 * 
	 * @param none.
	 * 
	 * @return size of the library.
	 */
	public int librarySize() {
		return foo.size();
	}

	/**
	 * Checks out a book from the library. Accessible by students.
	 * 
	 * @param {String} key - Title of the book to be checked out.
	 * 
	 * @return a boolean indicating if the book can be found and checked out.
	 */
	public boolean checkOut(String key) {
		try {
			if (foo.get(key).getCheckedIn()) { 
				foo.get(key).setCheckedIn(); 
				return true; 
			}
		} catch (Exception e) {
			
		}
		return false;
	}
	
	/**
	 * Checks in a book to the library. Accessible by students.
	 * 
	 * @param {String} key - Title of the book to be checked back in.
	 * 
	 * @return a boolean indicating if the book can be found and checked in.
	 */
	public boolean checkIn(String key) {
		try {
			if (!foo.get(key).getCheckedIn()) { 
				foo.get(key).setCheckedIn(); 
				return true;
			}
		} catch (Exception e) {
			
		}
		return false;
	}
	
	/**
	 * Checks a book's availability.
	 * 
	 * @param {String} key - Title of the book.
	 * 
	 * @return a boolean indicating the book's availability.
	 */
	public boolean isBookAvailable(String key) {
		return foo.get(key).getCheckedIn();
	}
}
