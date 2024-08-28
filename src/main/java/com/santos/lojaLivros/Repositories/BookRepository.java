package com.santos.lojaLivros.Repositories;

import com.santos.lojaLivros.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {


    //Lista todos os livros de uma determinada categoria
    @Query("SELECT obj from Book obj WHERE obj.category.id =:idCat ORDER BY titulo")
    List<Book> findAllByCategoria(@Param(value = "idCat") Integer idCat);
}
