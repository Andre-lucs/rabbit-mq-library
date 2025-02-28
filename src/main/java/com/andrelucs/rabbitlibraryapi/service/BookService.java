package com.andrelucs.rabbitlibraryapi.service;

import com.andrelucs.rabbitlibraryapi.model.dto.BookDTO;
import com.andrelucs.rabbitlibraryapi.repository.BookRepository;
import com.andrelucs.rabbitlibraryapi.repository.LoanRepositoy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final LoanRepositoy loanRepositoy;

    public BookService(BookRepository repository, LoanRepositoy loanRepositoy) {
        this.repository = repository;
        this.loanRepositoy = loanRepositoy;
    }

    public List<BookDTO> getBooks() {
        return repository.findAll().stream().map(BookDTO::fromModel).toList();
    }

    public void addBook(BookDTO bookDTO) {
        repository.save(BookDTO.toModel(bookDTO));
    }

    public void updateBook(Long id, BookDTO bookDTO) {
        repository.findById(id).ifPresent(book -> {
            book.setAuthor(bookDTO.getAuthor());
            book.setTitle(bookDTO.getTitle());
            repository.save(book);
        });
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    public Boolean isBookAvailable(String title) {
        return loanRepositoy.isAvailable(title);
    }
}
