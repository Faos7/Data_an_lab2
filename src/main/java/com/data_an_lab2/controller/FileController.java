package com.data_an_lab2.controller;


import com.data_an_lab2.entity.DomainObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.data_an_lab2.service.DomainService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    DomainService domainService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView uploadFile(@RequestParam("file") MultipartFile file) {// имена параметров (тут - "file") - из формы JSP.

        LOGGER.debug("Staring upload");

        String name = null;

        RedirectView redirectView = new RedirectView();

        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                String rootPath = "/home/faos7/upload/" ; //try also "C:\path\"
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                LOGGER.debug("uploaded: " + uploadedFile.getAbsolutePath());

                domainService.create(uploadedFile);

                redirectView.setUrl("http://localhost:8090/c");

            } catch (Exception e) {
                LOGGER.debug("You failed to upload " + name + " => " + e.toString());
                redirectView.setUrl("http://localhost:8090/");

            }
        } else {
            LOGGER.debug("You failed to upload " + name + " because the file was empty.");
            redirectView.setUrl("http://localhost:8090/");

        }
        // ok, redirect


        return redirectView;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getDomainPage(){
        LOGGER.debug("Getting upload page");
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/c")
    public ModelAndView getCalcPage(){
        LOGGER.debug("Getting calc page");
        DomainObject domainObject = domainService.getOneById(1L);
        return new ModelAndView("domainObject", "domainObject", domainObject);
    }
}