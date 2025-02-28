package com.andrelucs.rabbitlibraryapi.model.dto;

import com.andrelucs.rabbitlibraryapi.model.Book;

import java.util.Objects;

public class BookDTO {
    private String title;
    private String author;

    public BookDTO(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(title, bookDTO.title) && Objects.equals(author, bookDTO.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public static BookDTO fromModel(Book book) {
        return new BookDTO(book.getAuthor(), book.getTitle());
    }

    public static Book toModel(BookDTO bookDTO) {
        return new Book(null, bookDTO.getAuthor(), bookDTO.getTitle());
    }
}
