package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void addAuthorWithBooks() {

        Author jn = new Author();
        jn.setName("Joana Nimar");
        jn.setAge(34);
        jn.setGenre("History");

        Book jn01 = new Book();
        jn01.setIsbn("001-JN");
        jn01.setTitle("A History of Ancient Prague");

        Book jn02 = new Book();
        jn02.setIsbn("002-JN");
        jn02.setTitle("A People's History");

        Book jn03 = new Book();
        jn03.setIsbn("003-JN");
        jn03.setTitle("World History");

        jn.addBook(jn01);
        jn.addBook(jn02);
        jn.addBook(jn03);

        authorRepository.save(jn);
    }

    @Transactional
    public void addNewBook() {

        Author author = authorRepository.findByName("Joana Nimar");

        Book book = new Book();
        book.setIsbn("004-JN");
        book.setTitle("History Details");

        author.addBook(book); // use addBook() helper

        authorRepository.save(author);
    }

    @Transactional
    public void deleteLastBook() {

        Author author = authorRepository.findByName("Joana Nimar");
        List<Book> books = author.getBooks();

        author.removeBook(books.get(books.size() - 1));
    }

    @Transactional
    public void deleteFirstBook() {

        Author author = authorRepository.findByName("Joana Nimar");
        List<Book> books = author.getBooks();

        author.removeBook(books.get(0));
    }
}
