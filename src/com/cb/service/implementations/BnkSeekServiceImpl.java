package com.cb.service.implementations;

import com.cb.model.BnkSeekEntity;
import com.cb.repository.BnkSeekRepository;
import com.cb.service.BnkSeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class BnkSeekServiceImpl implements BnkSeekService {

    private final BnkSeekRepository repository;

    @Autowired
    public BnkSeekServiceImpl(BnkSeekRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void generateTestData() {
        //save(new BnkSeekEntity());
    }

    @Override
    public BnkSeekEntity save(BnkSeekEntity bnkSeekEntity) {
        return repository.save(bnkSeekEntity);
    }

    @Override
    public List<BnkSeekEntity> findAll() {
        return repository.findAll();
    }
}
