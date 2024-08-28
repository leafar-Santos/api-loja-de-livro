package com.santos.lojaLivros.Resources;

import com.santos.lojaLivros.dtos.BookDTO;
import com.santos.lojaLivros.dtos.CategoryDTO;
import com.santos.lojaLivros.entities.Book;
import com.santos.lojaLivros.entities.Category;
import com.santos.lojaLivros.services.CategoryService;
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
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    //Category sem DTO retorna a categoria com todos os livros cadastrados
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){

        Category obj = categoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //Cetegory com DTO retorna apenas as categorias
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<Category> list = categoryService.findAll();
        List<CategoryDTO> listDTO = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<Category> create(@Valid @RequestBody Category obj){
        obj = categoryService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return  ResponseEntity.created(uri).body(obj);
        //return  ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO>update(@PathVariable Integer id,@Valid @RequestBody CategoryDTO obj){
        Category newObj = categoryService.update(id, obj);
        return ResponseEntity.ok().body(new CategoryDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
