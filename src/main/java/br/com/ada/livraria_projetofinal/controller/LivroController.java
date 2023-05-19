package br.com.ada.livraria_projetofinal.controller;

import br.com.ada.livraria_projetofinal.model.dto.LivroDTO;
import br.com.ada.livraria_projetofinal.service.LivroService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/livro",  produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class LivroController {
    @Autowired
    LivroService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvar(@RequestBody @Valid LivroDTO livroDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.salvar(livroDto));
        } catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
