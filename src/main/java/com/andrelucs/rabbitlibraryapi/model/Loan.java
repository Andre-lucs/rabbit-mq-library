package com.andrelucs.rabbitlibraryapi.model;

import com.andrelucs.rabbitlibraryapi.model.dto.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Loan {
    @Id
    @GeneratedValue
    private Long id;
    private String bookTitle;
    private String requester;
    private LocalDate expectedReturnDate;
    private Status status;

    public Loan(Long id, String bookTitle, String requester, LocalDate expectedReturnDate, Status status) {
        this.bookTitle = bookTitle;
        this.expectedReturnDate = expectedReturnDate;
        this.id = id;
        this.requester = requester;
        this.status = status;
    }

    public Loan() {

    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id) && Objects.equals(bookTitle, loan.bookTitle) && Objects.equals(requester, loan.requester) && Objects.equals(expectedReturnDate, loan.expectedReturnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookTitle, requester, expectedReturnDate);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "bookTitle='" + bookTitle + '\'' +
                ", id=" + id +
                ", requester='" + requester + '\'' +
                ", expectedReturnDate=" + expectedReturnDate +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
