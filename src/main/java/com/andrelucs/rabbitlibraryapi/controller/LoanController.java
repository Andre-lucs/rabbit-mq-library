package com.andrelucs.rabbitlibraryapi.controller;

import com.andrelucs.rabbitlibraryapi.model.dto.LoanRequestDTO;
import com.andrelucs.rabbitlibraryapi.service.LoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<String> requestLoan(@RequestBody LoanRequestDTO loanRequestDTO) throws JsonProcessingException {
        loanService.requestLoan(loanRequestDTO);
        String requester = loanRequestDTO.getRequester();
        URI requestsPath = ServletUriComponentsBuilder.fromCurrentRequest().path("/requester/{requester}").buildAndExpand(requester).toUri();
        return ResponseEntity.created(requestsPath).body("Loan request sent successfully! Your requests are on the specified Header Location field.");
    }

    @GetMapping("/{bookTitle}")
    public ResponseEntity<List<LoanRequestDTO>> getActiveRequestsFromBook(@PathVariable String bookTitle) {
        return ResponseEntity.ok(loanService.getRequestsFromBook(bookTitle));
    }

    @GetMapping("/requester/{requester}")
    public ResponseEntity<List<LoanRequestDTO>> getLoansByRequester(@PathVariable String requester) {
        return ResponseEntity.ok(loanService.getLoansByRequester(requester));
    }

}
