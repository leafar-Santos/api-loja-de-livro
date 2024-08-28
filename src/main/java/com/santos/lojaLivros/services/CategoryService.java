package com.santos.lojaLivros.services;

import com.santos.lojaLivros.Repositories.CategoryRepository;
import com.santos.lojaLivros.dtos.BookDTO;
import com.santos.lojaLivros.dtos.CategoryDTO;
import com.santos.lojaLivros.entities.Book;
import com.santos.lojaLivros.entities.Category;
import com.santos.lojaLivros.services.exceptions.ExcecaoIntegridadeDados;
import com.santos.lojaLivros.services.exceptions.ExcecaoObjetoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category findById(Integer id){
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElseThrow(() -> new ExcecaoObjetoNaoEncontrado("Objeto não encotrado! Id: " + id + " - Tipo: " + Category.class.getName()));
    }


    public List<Category>findAll(){
        return categoryRepository.findAll();
    }

    public Category create(Category obj){
        obj.setId(null);
        return categoryRepository.save(obj);

    }

    public Category update(Integer id, CategoryDTO objDTO) {
        Category newObj = findById(id); //Verifica se a categoria existe antes de atualizar
        newObj.setNome(objDTO.getNome());
        newObj.setDescricao(objDTO.getDescricao());
        return categoryRepository.save(newObj);
    }

    public void delete(Integer id) {
        //Verifica se a categoria existe antes de atualizar
        findById(id);
        try{
            categoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new ExcecaoIntegridadeDados("Categoria não pode ser deletada pois possui livros associados.");
        }

    }
}
