package br.com.ada.livraria_projetofinal.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "livro")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "'titulo' é obrigatório")
    private String titulo;
    @NotBlank(message = "'resumo' é obrigatório")
    @Size(max = 500, message = "Tamanho máximo do 'resumo' é de 500 caracteres")
    private String resumo;
    private String sumario;
    @NotNull(message = "'preco' é obrigatório")
    @DecimalMin(value="20.0")
    private Double preco;
    @NotNull(message = "Numero de paginas é obrigatório")
    @Min(value = 100)
    private Integer paginas;
    @NotBlank(message = "'isbn' é obrigatório")
    @Column(unique = true)
    private String isbn;
    @Future(message = "Data obrigatória no futuro")
    private Date dataPublicacao;

}
