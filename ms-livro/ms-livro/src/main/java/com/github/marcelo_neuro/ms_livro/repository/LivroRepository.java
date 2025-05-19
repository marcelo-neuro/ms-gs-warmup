package com.github.marcelo_neuro.ms_livro.repository;

import com.github.marcelo_neuro.ms_livro.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
