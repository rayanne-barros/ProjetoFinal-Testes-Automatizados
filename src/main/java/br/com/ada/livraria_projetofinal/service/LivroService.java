package br.com.ada.livraria_projetofinal.service;

import br.com.ada.livraria_projetofinal.model.dto.LivroDTO;
import br.com.ada.livraria_projetofinal.model.entity.LivroEntity;
import br.com.ada.livraria_projetofinal.model.mapper.LivroMapper;
import br.com.ada.livraria_projetofinal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    LivroRepository repository;

    @Autowired
     LivroMapper mapper;


    public LivroDTO salvar(LivroDTO livroDto){
        LivroEntity livro = mapper.update(livroDto);

        livro = repository.save(livro);

        return mapper.update(livro);
    }

    public List<LivroDTO> listar(){
        List<LivroEntity> listasEntities = repository.findAll();
        return mapper.updateListDTO(listasEntities);
    }
}
