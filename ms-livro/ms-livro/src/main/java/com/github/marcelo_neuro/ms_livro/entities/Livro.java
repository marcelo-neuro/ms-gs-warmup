package com.github.marcelo_neuro.ms_livro.entities;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_livro")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer anoPublicacao;
}
