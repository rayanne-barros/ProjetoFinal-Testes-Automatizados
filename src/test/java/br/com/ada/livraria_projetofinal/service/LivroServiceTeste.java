package br.com.ada.livraria_projetofinal.service;

import br.com.ada.livraria_projetofinal.model.dto.LivroDTO;
import br.com.ada.livraria_projetofinal.model.mapper.LivroMapper;
import br.com.ada.livraria_projetofinal.repository.LivroRepository;
import br.com.ada.livraria_projetofinal.repository.LivroRepositoryTeste;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

public class LivroServiceTeste {
    private LivroMapper livroMapper = new LivroMapper();
    private LivroRepository repository = new LivroRepositoryTeste();

    private LivroService service = new LivroService();

    @Test
    public void tituloNulo(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setResumo("Resumo do livro");
        livroDTO.setSumario("Sumário");
        livroDTO.setPreco(20.00);
        livroDTO.setPaginas(100);
        livroDTO.setIsbn("1234567890123");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }

    @Test
    public void livroComResumoNulo(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("Título");
        livroDTO.setSumario("Sumário");
        livroDTO.setPreco(20.00);
        livroDTO.setPaginas(100);
        livroDTO.setIsbn("1234567890123");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }

    @Test
    public void livroComResumoMaiorQue500Caracteres(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("Título");
        livroDTO.setResumo("Resumo maior que 500 caracteres xxxxxxxxxxxxxxx"+
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        livroDTO.setSumario("Sumário");
        livroDTO.setPreco(20.00);
        livroDTO.setPaginas(100);
        livroDTO.setIsbn("1234567890123");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }

    @Test
    public void livroComPrecoNulo(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("Título");
        livroDTO.setResumo("Resumo do livro");
        livroDTO.setSumario("Sumário");
        livroDTO.setPaginas(100);
        livroDTO.setIsbn("1234567890123");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }

    @Test
    public void livroComPrecoMenorQue20(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("Título");
        livroDTO.setResumo("Resumo do livro");
        livroDTO.setSumario("Sumário");
        livroDTO.setPreco(19.99);
        livroDTO.setPaginas(100);
        livroDTO.setIsbn("1234567890123");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }

    @Test
    public void livroComNumeroDePaginasMenorQuePermitido(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("Título");
        livroDTO.setResumo("Resumo do livro");
        livroDTO.setSumario("Sumário");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(99);
        livroDTO.setIsbn("1234567890123");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }

    @Test
    public void livroComNumeroDePaginasNulo(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("Título");
        livroDTO.setResumo("Resumo do livro");
        livroDTO.setSumario("Sumário");
        livroDTO.setPreco(20.0);
        livroDTO.setIsbn("1234567890123");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }

    @Test
    public void livroComIsbnNulo(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("Título");
        livroDTO.setResumo("Resumo do livro");
        livroDTO.setSumario("Sumário");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(99);
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }

    @Test
    public void livroComDataNoPresente(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("Título");
        livroDTO.setResumo("Resumo do livro");
        livroDTO.setSumario("Sumário");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(99);
        livroDTO.setIsbn("1234567890123");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now()));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }

    @Test
    public void livroComDataNoPassado(){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("Título");
        livroDTO.setResumo("Resumo do livro");
        livroDTO.setSumario("Sumário");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(99);
        livroDTO.setIsbn("1234567890123");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().minusDays(2)));

        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(livroDTO));
    }






}
