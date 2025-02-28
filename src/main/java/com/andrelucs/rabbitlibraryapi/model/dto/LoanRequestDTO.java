package com.andrelucs.rabbitlibraryapi.model.dto;

import com.andrelucs.rabbitlibraryapi.model.Loan;
import com.andrelucs.rabbitlibraryapi.model.dto.enums.Status;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

public class LoanRequestDTO{
    private static final ObjectMapper mapper = new ObjectMapper();
    private String bookTitle;
    private String requester;
    private Status status;
    private LocalDate expectedReturnDate;

    public LoanRequestDTO(String bookTitle, String requester, LocalDate expectedReturnDate, Status status) {
        this.bookTitle = bookTitle;
        this.expectedReturnDate = expectedReturnDate;
        this.requester = requester;
        this.status = status;
    }

    public static LoanRequestDTO fromModel(Loan loan) {
        return new LoanRequestDTO(loan.getBookTitle(), loan.getRequester(), loan.getExpectedReturnDate(), loan.getStatus());
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Loan toModel() {
        return new Loan(null, bookTitle, requester, expectedReturnDate, status);
    }

    @Override
    public String toString() {
        return "LoanRequestDTO{" +
                "bookTitle='" + bookTitle + '\'' +
                ", requester='" + requester + '\'' +
                ", status=" + status +
                ", expectedReturnDate=" + expectedReturnDate +
                '}';
    }
}
