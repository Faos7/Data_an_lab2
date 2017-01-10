package com.data_an_lab2.repository;

import com.data_an_lab2.entity.DomainObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by faos7 on 09.01.17.
 */
@Transactional
public interface DomainObjectRepository extends JpaRepository<DomainObject, Long> {

}