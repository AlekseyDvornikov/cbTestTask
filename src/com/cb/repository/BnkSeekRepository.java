package com.cb.repository;

import com.cb.model.BnkSeekEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Transactional(propagation = Propagation.MANDATORY)
    public interface BnkSeekRepository extends CrudRepository<BnkSeekEntity, Long> {

    List<BnkSeekEntity> findAll();

}
