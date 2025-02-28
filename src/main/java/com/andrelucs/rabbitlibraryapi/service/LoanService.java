package com.andrelucs.rabbitlibraryapi.service;

import com.andrelucs.rabbitlibraryapi.exceptions.InexistentBookException;
import com.andrelucs.rabbitlibraryapi.model.Loan;
import com.andrelucs.rabbitlibraryapi.model.dto.LoanRequestDTO;
import com.andrelucs.rabbitlibraryapi.model.dto.enums.Status;
import com.andrelucs.rabbitlibraryapi.repository.BookRepository;
import com.andrelucs.rabbitlibraryapi.repository.LoanRepositoy;
import com.andrelucs.rabbitlibraryapi.service.messaging.producer.LoanProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanProducer loanProducer;
    private final LoanRepositoy loanRepositoy;
    private final BookRepository bookRepository;

    public LoanService(LoanProducer loanProducer, LoanRepositoy loanRepositoy, BookRepository bookRepository) {
        this.loanProducer = loanProducer;
        this.loanRepositoy = loanRepositoy;
        this.bookRepository = bookRepository;
    }

    public void requestLoan(LoanRequestDTO loanRequestDTO) throws JsonProcessingException {
        if(!bookRepository.existsByTitle(loanRequestDTO.getBookTitle())) {
            throw new InexistentBookException("Book not found: " + loanRequestDTO.getBookTitle());
        }
        loanRequestDTO.setStatus(Status.PENDING);
        Loan savedLoan = loanRepositoy.save(loanRequestDTO.toModel());
        loanProducer.sendLoanRequest(savedLoan.getId());
    }

    public void processLoanRequest(Long requestId) {
        Loan loan = loanRepositoy.findById(requestId).orElse(null);
        if (loan == null) {
            System.out.println("Loan request not found: " + requestId);
            return;
        }
        boolean isAvaliable = loanRepositoy.isAvailable(loan.getBookTitle());

        if (isAvaliable) {
            if(loan.getExpectedReturnDate() == null) {
                loan.setExpectedReturnDate(LocalDate.now().plusDays(7));
            }
            loan.setStatus(Status.ACCEPTED);
            System.out.println("Loan request accepted: " + loan);
            loanRepositoy.save(loan);
        } else {
            System.out.println("Loan request denied: " + loan);
            loan.setStatus(Status.REJECTED);
            loan.setExpectedReturnDate(null);
            loanRepositoy.save(loan);
        }
    }

    public List<LoanRequestDTO> getRequestsFromBook(String bookTitle) {
        return loanRepositoy.getLoansByBookTitle(bookTitle).stream().map(LoanRequestDTO::fromModel).toList();
    }

    public List<LoanRequestDTO> getLoansByRequester(String requester) {
        return loanRepositoy.getLoansByRequester(requester).stream().map(LoanRequestDTO::fromModel).toList();
    }
}
