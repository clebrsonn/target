package br.com.hyteck.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IServices<T> {

    default T update(Long id, T entity){
        return findById(id).map(saved ->  {
            BeanUtils.copyProperties(entity, saved, "id");
            return create(saved);
        }).orElseThrow(() -> new EmptyResultDataAccessException(1));

    }

    Page<T> findall(Pageable pageable);

    Optional<T> findById(Long id);

    T create(T entity);
}
