package com.andrelucs.rabbitlibraryapi.repository;

import com.andrelucs.rabbitlibraryapi.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepositoy extends JpaRepository<Loan, Long> {
    default boolean isAvailable(String bookTitle){
        List<Loan> books = this.findByBookTitle(bookTitle);
        for (Loan book : books) {
            if (book.getExpectedReturnDate() != null && book.getExpectedReturnDate().isAfter(LocalDate.now())) {
                return false;
            }
        }
        return true;
    };

    List<Loan> findByBookTitle(String bookTitle);

    List<Loan> getLoansByBookTitleAndExpectedReturnDateBefore(String bookTitle, LocalDate date);

    List<Loan> getLoansByRequester(String requester);

    List<Loan> getLoansByBookTitle(String bookTitle);
}
