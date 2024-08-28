package com.santos.lojaLivros.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo nome é obrigatório")
    @Length(min = 3, max = 100, message = "O campo nome deve possuir entre 3 e 100 caracteres.")
    private String nome;

    @NotEmpty(message = "Campo descrição é obrigatório")
    @Length(min = 3, max = 100, message = "O campo descriçao deve possuir entre 3 e 200 caracteres.")
    private String descricao;

    //Uma categoria pode possuir um ou mais livros
    //Uma categoria para vários livros
    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();
}
