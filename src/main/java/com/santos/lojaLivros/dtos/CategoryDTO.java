package com.santos.lojaLivros.dtos;

import com.santos.lojaLivros.entities.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    //Não possui a lista de livros

    private Integer id;

    @NotEmpty(message = "Campo nome é obrigatório")
    @Length(min = 3, max = 100, message = "O campo nome deve possuir entre 3 e 100 caracteres.")
    private String nome;

    @NotEmpty(message = "Campo descrição é obrigatório")
    @Length(min = 3, max = 100, message = "O campo descriçao deve possuir entre 3 e 200 caracteres.")
    private String descricao;

    public CategoryDTO(Category obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.descricao = obj.getDescricao();
    }
}
