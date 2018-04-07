package com.cb.service;

import com.cb.model.BnkSeekEntity;

import java.util.List;

public interface BnkSeekService {

    BnkSeekEntity save(BnkSeekEntity bnkSeekEntity);

    List<BnkSeekEntity> findAll();

}