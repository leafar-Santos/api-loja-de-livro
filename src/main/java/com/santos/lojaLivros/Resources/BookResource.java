package com.santos.lojaLivros.Resources;

import com.santos.lojaLivros.dtos.BookDTO;
import com.santos.lojaLivros.entities.Book;
import com.santos.lojaLivros.services.BookService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/books")
public class BookResource {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable Integer id){
        Book obj = bookService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping
    public ResponseEntity<List<BookDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat){
        //Estilo da URL localhost:8080/books?categoria=1
        List<Book> list = bookService.fidAll(id_cat);
        List<BookDTO> listDTO = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDTO>update( @PathVariable Integer id,@Valid @RequestBody BookDTO obj){
        Book newObj = bookService.update(id, obj);
        return ResponseEntity.ok().body(new BookDTO(newObj));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Book>updatePatch( @PathVariable Integer id, @Valid @RequestBody BookDTO obj){
        Book newObj = bookService.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }


    //Um livro obrigatoriamente possui uma categoria
    @PostMapping(value = "/create")
    public ResponseEntity<Book> create(@RequestParam(value= "categoria", defaultValue = "0")Integer id_cat, @Valid @RequestBody Book obj){
        Book newObj = bookService.create(id_cat, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/books/{id}").buildAndExpand(newObj.getId()).toUri();
        //return ResponseEntity.created(uri).build();
        return ResponseEntity.created(uri).body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
