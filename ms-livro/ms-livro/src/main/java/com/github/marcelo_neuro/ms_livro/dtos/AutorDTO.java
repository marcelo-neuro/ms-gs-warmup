package com.github.marcelo_neuro.ms_livro.dtos;

import com.github.marcelo_neuro.ms_livro.entities.Autor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AutorDTO {

    private Long id;

    @NotBlank(message = "Nome requerido.")
    @Size(min = 3, max = 100, message = "O nome do autor deve conter entre 3 e 100 caracteres")
    private String nome;
    @NotBlank(message = "Email requerido.")
    @Size(max = 50, message = "O nome do autor deve conter até 50 caracteres.")
    private String email;

    public AutorDTO(Autor entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
    }
}
