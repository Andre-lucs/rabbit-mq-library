package com.andrelucs.rabbitlibraryapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 3, max = 100)
    private String title;
    @Size(min = 3, max = 100)
    private String author;

    public Book(Long id, String author, String title) {
        this.author = author;
        this.id = id;
        this.title = title;
    }

    public Book() {

    }

    public @Size(min = 3, max = 100) String getAuthor() {
        return author;
    }

    public void setAuthor(@Size(min = 3, max = 100) String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 3, max = 100) String getTitle() {
        return title;
    }

    public void setTitle(@Size(min = 3, max = 100) String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
