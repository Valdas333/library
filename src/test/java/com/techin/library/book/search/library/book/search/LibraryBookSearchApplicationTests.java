package com.techin.library.book.search.library.book.search;

import com.techin.library.book.search.library.book.search.controllers.BookSearchController;
import com.techin.library.book.search.library.book.search.controllers.BookSearchFormController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LibraryBookSearchApplicationTests {
	@Autowired
	BookSearchController bookSearchController;
	@Autowired
	BookSearchFormController bookSearchFormController;
	@Test
	void contextLoads() {
		assertNotNull(bookSearchController);
		assertNotNull(bookSearchFormController);
		assertNotNull(bookSearchController);
	}

}
