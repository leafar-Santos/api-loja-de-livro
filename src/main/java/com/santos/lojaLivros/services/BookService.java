package com.santos.lojaLivros.services;

import com.santos.lojaLivros.Repositories.BookRepository;
import com.santos.lojaLivros.dtos.BookDTO;
import com.santos.lojaLivros.entities.Book;
import com.santos.lojaLivros.entities.Category;
import com.santos.lojaLivros.services.exceptions.ExcecaoObjetoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private CategoryService categoryService;

    public Book findById(Integer id){
        Optional<Book> obj = bookRepository.findById(id);
        return obj.orElseThrow(() -> new ExcecaoObjetoNaoEncontrado("Objeto não econtrado. Id: " + id + " Tipo: " + Book.class.getName()));

    }

    public List<Book> fidAll(Integer idCat) {
        //Verificando se a categoria existe
        categoryService.findById(idCat);
        return bookRepository.findAllByCategoria(idCat);
    }

    public Book update(Integer id, BookDTO obj) {
        Book newObj = findById(id);
        newObj.setTitulo(obj.getTitulo());
        newObj.setNomeAutor(obj.getNomeAutor());
        newObj.setTexto(obj.getTexto());
        return bookRepository.save(newObj);
    }

    public Book create(Integer idCat, Book obj) {
        obj.setId(null);
        Category cat = categoryService.findById(idCat);
        obj.setCategory(cat);//Fazendo o livro conhecer a categoria
        return bookRepository.save(obj);
    }

    public void delete(Integer id) {
        Book obj = findById(id);
        bookRepository.delete(obj);
    }
}
