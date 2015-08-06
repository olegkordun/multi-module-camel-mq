package me.kordun.office.front.controller;


import me.kordun.json.CompanyJson;
import me.kordun.office.front.service.CompanyFrontService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class CompanyController {
    private static final Logger log = LoggerFactory.getLogger("CompanyController");

    @Autowired
    private CompanyFrontService companyFrontService;

    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyJson createCompany( @RequestBody @Valid CompanyJson createJson)
            throws MethodArgumentNotValidException, BindException {
        log.info("Got company create request");
        CompanyJson resultJson = companyFrontService.createCompany(createJson);
        return resultJson;
    }

    @RequestMapping(value = "/companies/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public CompanyJson updateCompany(@PathVariable(value = "id") Long id, @RequestBody @Valid CompanyJson companyJson)
            throws MethodArgumentNotValidException, BindException {
        log.info("Got company update id={}", id);
        CompanyJson resultJson = companyFrontService.updateCompany(id, companyJson);
        return resultJson;
    }

    @RequestMapping(value = "/companies/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CompanyJson getCompany(@PathVariable(value = "id") Long id)
            throws MethodArgumentNotValidException, BindException {
        log.info("Got company request id={}", id);

        CompanyJson resultJson = companyFrontService.getCompany(id);
        return resultJson;
    }

    @RequestMapping(value = "/companies/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyJson> getCompanyList()
            throws MethodArgumentNotValidException, BindException {
        log.info("Get all companies");

        List<CompanyJson> result = companyFrontService.getAllCompanies();
        return result;
    }


}
