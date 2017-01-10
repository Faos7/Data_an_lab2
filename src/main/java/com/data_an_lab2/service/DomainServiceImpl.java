package com.data_an_lab2.service;

import com.data_an_lab2.entity.DomainObject;
import com.data_an_lab2.misc.DataCalculator;
import com.data_an_lab2.misc.DataLoader;
import com.data_an_lab2.repository.DomainObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by faos7 on 09.01.17.
 */
@Service
public class DomainServiceImpl implements DomainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainService.class);

    @Autowired
    private DomainObjectRepository domainObjectRepository;

    @Override
    public DomainObject getOneById(long id) {
        LOGGER.debug("Getting one by id");
        DataCalculator calculator = DataCalculator.getInstance();

        DomainObject domainObject = new DomainObject(calculator);
        return domainObject;
    }

    @Override
    public DomainObject create(File file) {
        LOGGER.debug("Creating domain");
        DataCalculator.initializeInstance(
                DataLoader.load(file)
        );

        DataCalculator calculator = DataCalculator.getInstance();
        LOGGER.debug("Calc initialized");
        DomainObject domainObject = new DomainObject(calculator);
        //LOGGER.debug("Saving domain");
        //domainObjectRepository.save(domainObject);
        LOGGER.debug("saving success");
        return domainObject;
    }

    @Override
    public DomainObject deleteAnomalius() {
        LOGGER.debug("Deleting anomalious val");
        DataCalculator calculator = DataCalculator.getInstance();
        calculator.removeAnomalies();
        calculator.reinitializeInstance();

        DomainObject domainObject = new DomainObject(calculator);
        return domainObject;
    }
}
