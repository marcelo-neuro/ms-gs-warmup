package com.github.marcelo_neuro.ms_livro.service;

import com.github.marcelo_neuro.ms_livro.dtos.LivroDTO;
import com.github.marcelo_neuro.ms_livro.entities.Livro;
import com.github.marcelo_neuro.ms_livro.repository.AutorRepository;
import com.github.marcelo_neuro.ms_livro.repository.LivroRepository;
import com.github.marcelo_neuro.ms_livro.service.exceptions.DatabaseException;
import com.github.marcelo_neuro.ms_livro.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional(readOnly = true)
    public List<LivroDTO> findAll() {
        return livroRepository.findAll().stream().map(LivroDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public LivroDTO findById(Long id) {
        return new LivroDTO(livroRepository.findById(id).orElseThrow(() -> {
           return new ResourceNotFoundException("Livro não encontrado; id: " + id);
        }));
    }

    @Transactional
    public LivroDTO create(LivroDTO dto) {
        try {
            Livro entity = new Livro();
            dto2Entity(dto, entity);
            entity = livroRepository.save(entity);
            return new LivroDTO(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade referencial id:" + dto.getId());
        }
    }

    @Transactional
    public LivroDTO update(Long id, LivroDTO dto) {
        try {
            Livro entity = livroRepository.getReferenceById(id);
            dto2Entity(dto, entity);
            entity = livroRepository.save(entity);
            return new LivroDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Livro não encontrado; id:" + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade referencial id:" + dto.getId());
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Livro não encontrado; id:" + id);
        }
        livroRepository.deleteById(id);
    }

    private void dto2Entity(LivroDTO dto, Livro entity) {
        entity.setTitulo(dto.getTitulo());
        entity.setAnoPublicacao(dto.getAnoPublicacao());

        entity.getAutores().clear();
        dto.getAutores().forEach(a -> entity.getAutores().add(autorRepository.getReferenceById(a.getId())));
    }

}
