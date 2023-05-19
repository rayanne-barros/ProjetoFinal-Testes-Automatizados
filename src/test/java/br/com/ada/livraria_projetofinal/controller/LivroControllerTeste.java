package br.com.ada.livraria_projetofinal.controller;

import br.com.ada.livraria_projetofinal.model.dto.LivroDTO;
import br.com.ada.livraria_projetofinal.model.entity.LivroEntity;
import br.com.ada.livraria_projetofinal.model.mapper.LivroMapper;
import br.com.ada.livraria_projetofinal.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LivroControllerTeste {

    @BeforeAll
    public void init(){
        LivroDTO livroPadrao = new LivroDTO();

        livroPadrao.setTitulo("A Menina que roubava livros");
        livroPadrao.setResumo("A trajetória de Liesel Meminger é contada por uma narradora mórbida," +
                " surpreendentemente simpática. Ao perceber que a pequena ladra de livros lhe escapa," +
                " a Morte afeiçoa-se à menina e rastreia suas pegadas de 1939 a 1943.");
        livroPadrao.setSumario("");
        livroPadrao.setPreco(69.90);
        livroPadrao.setPaginas(480);
        livroPadrao.setIsbn("978-85-98078-17-5");
        livroPadrao.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(1)));

        repository.save(livroMapper.update(livroPadrao));
    }

    @Autowired
    private LivroController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroMapper livroMapper;

    @Test
    public void carregouContexto(){
        Assertions.assertTrue(controller != null);
    }

    @Test
    public void testeOk() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/livro"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void criarLivroSucesso() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("Titulo");
        livroDTO.setResumo("Resumo menor que 500 caracteres");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(124);
        livroDTO.setIsbn("85-98070");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        LivroEntity livro = repository.findByIsbn("85-98070");

        Assertions.assertEquals("Titulo", livro.getTitulo());
        Assertions.assertEquals("Resumo menor que 500 caracteres", livro.getResumo());
        Assertions.assertEquals("1-Sumario 2- Sumario", livro.getSumario());
        Assertions.assertEquals(20, livro.getPreco());
        Assertions.assertEquals(124, livro.getPaginas());
        Assertions.assertEquals("85-98070", livro.getIsbn());
        Assertions.assertEquals(Date.valueOf(LocalDate.now().plusDays(2)), livro.getDataPublicacao());
    }

    @Test
    public void livroSemTitulo() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("");
        livroDTO.setResumo("Resumo menor que 500 caracteres");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(124);
        livroDTO.setIsbn("85-98071");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void livroComResumoDe500Caracteres() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("Titulo");
        livroDTO.setResumo("Resumo com 500caracteresxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxx");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(124);
        livroDTO.setIsbn("85-98072");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void livroComResumoMaiorQue500Caracteres() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("Titulo");
        livroDTO.setResumo("Resumo maior que 500 caracteres xxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(124);
        livroDTO.setIsbn("85-98073");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void livroComPrecoMenorQuePermitido() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("Titulo");
        livroDTO.setResumo("Resumo menor que 500 caracteres");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(19.99);
        livroDTO.setPaginas(124);
        livroDTO.setIsbn("85-98074");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void livroComNumeroDePaginasMenorQueOPermitido() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("Titulo");
        livroDTO.setResumo("Resumo menor que 500 caracteres");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(99);
        livroDTO.setIsbn("85-98075");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void livroComDataNoPresente() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("Titulo");
        livroDTO.setResumo("Resumo menor que 500 caracteres");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(124);
        livroDTO.setIsbn("85-98076");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now()));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void livroComDataNoPassado() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("Titulo");
        livroDTO.setResumo("Resumo menor que 500 caracteres");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(124);
        livroDTO.setIsbn("85-98077");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().minusDays(3)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void livroComIsbnVazio() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("");
        livroDTO.setResumo("Resumo menor que 500 caracteres");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(124);
        livroDTO.setIsbn("");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void livroComIsbnRepetido() throws Exception {
        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setTitulo("");
        livroDTO.setResumo("Resumo menor que 500 caracteres");
        livroDTO.setSumario("1-Sumario 2- Sumario");
        livroDTO.setPreco(20.0);
        livroDTO.setPaginas(124);
        livroDTO.setIsbn("978-85-98078-17-5");
        livroDTO.setDataPublicacao(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(livroDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
