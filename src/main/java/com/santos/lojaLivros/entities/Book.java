package com.santos.lojaLivros.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Campo titulo é obrigatório")
    @Length(min = 3, max = 50, message = "O campo nome deve possuir entre 3 e 50 caracteres.")
    private String titulo;

    @NotEmpty(message = "Campo Nome do Autor é obrigatório")
    @Length(min = 3, max = 50, message = "Campo Nome do Autor deve possuir entre 3 e 100 caracteres.")
    private String nomeAutor;

    @NotEmpty(message = "Campo texto é obrigatório")
    @Length(min = 10, max = 2000000, message = "Campo Texto deve possuir entre 10 e 2.000.000 caracteres.")
    private String texto;

    //O livro obrigatoriamente deve possuir uma categoria
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore //Retorna apenas o livro sem retornar a categoria
    private Category category;
}
