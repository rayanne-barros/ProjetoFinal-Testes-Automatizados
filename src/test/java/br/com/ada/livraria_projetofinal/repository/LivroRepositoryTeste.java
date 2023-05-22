package br.com.ada.livraria_projetofinal.repository;

import br.com.ada.livraria_projetofinal.model.entity.LivroEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class LivroRepositoryTeste implements LivroRepository{
    @Override
    public LivroEntity findByIsbn(String isbn) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends LivroEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends LivroEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<LivroEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public LivroEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public LivroEntity getById(Long aLong) {
        return null;
    }

    @Override
    public LivroEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends LivroEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends LivroEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends LivroEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends LivroEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends LivroEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends LivroEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends LivroEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends LivroEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends LivroEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LivroEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<LivroEntity> findAll() {
        return null;
    }

    @Override
    public List<LivroEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(LivroEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LivroEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<LivroEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<LivroEntity> findAll(Pageable pageable) {
        return null;
    }
}
