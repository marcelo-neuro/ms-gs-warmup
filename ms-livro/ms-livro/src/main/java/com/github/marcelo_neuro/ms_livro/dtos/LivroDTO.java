package com.github.marcelo_neuro.ms_livro.dtos;

import com.github.marcelo_neuro.ms_livro.entities.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class LivroDTO {

    private Long id;

    @NotBlank(message = "Título requerido.")
    @Size(min = 3, max = 150, message = "O título do livro deve conter entre 3 e 150 caracteres")
    private String titulo;
    @NotBlank(message = "Ano de publicação requerido.")
    @Positive(message = "Ano de publicação deve ser positivo.")
    private Integer anoPublicacao;

    private Set<AutorDTO> autores = new HashSet<>();

    public LivroDTO(Livro entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
        anoPublicacao = entity.getAnoPublicacao();

        entity.getAutores().forEach((a) -> autores.add(new AutorDTO(a)));
    }
}
