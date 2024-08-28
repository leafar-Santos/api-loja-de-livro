package com.santos.lojaLivros.dtos;


import com.santos.lojaLivros.entities.Book;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Campo titulo é obrigatório")
    @Length(min = 3, max = 50, message = "O campo nome deve possuir entre 3 e 50 caracteres.")
    private String titulo;

    @NotEmpty(message = "Campo Nome do Autor é obrigatório")
    @Length(min = 3, max = 50, message = "Campo Nome do Autor deve possuir entre 3 e 100 caracteres.")
    private String nomeAutor;

    @NotEmpty(message = "Campo textoé obrigatório")
    @Length(min = 10, max = 2000000, message = "Campo Nome do Autor deve possuir entre 10 e 2.000.000 caracteres.")
    private String texto;

    public BookDTO(Book obj) {
        super();
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.nomeAutor = obj.getNomeAutor();
        this.texto = obj.getTexto();
    }
}
