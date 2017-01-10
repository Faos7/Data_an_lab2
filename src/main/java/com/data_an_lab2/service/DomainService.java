package com.data_an_lab2.service;

import com.data_an_lab2.entity.DomainObject;

import java.io.File;

/**
 * Created by faos7 on 09.01.17.
 */
public interface DomainService {
    DomainObject getOneById(long id);
    DomainObject create(File file);
    DomainObject deleteAnomalius();
}
