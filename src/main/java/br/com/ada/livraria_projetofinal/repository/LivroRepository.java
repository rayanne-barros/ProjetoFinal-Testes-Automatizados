package br.com.ada.livraria_projetofinal.repository;

import br.com.ada.livraria_projetofinal.model.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    LivroEntity findByIsbn(String isbn);
}
