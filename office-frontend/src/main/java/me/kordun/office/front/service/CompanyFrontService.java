package me.kordun.office.front.service;

import me.kordun.json.CompanyJson;
import me.kordun.messaging.dto.CompanyDTO;

import me.kordun.messaging.routing.Routes;

import me.kordun.messaging.service.CompanyServiceInterface;
import org.apache.camel.Produce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static me.kordun.office.front.converter.CompanyJsonConverter.fromJson;
import static me.kordun.office.front.converter.CompanyJsonConverter.toJson;
import static java.util.stream.Collectors.toList;

@Service
public class CompanyFrontService  {
    private static final Logger log = LoggerFactory.getLogger("CompanyController");
    @Produce(uri = Routes.COMPANY_SERVICE)
    private CompanyServiceInterface companyService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompanyJson createCompany(CompanyJson json) {

        CompanyDTO response = getCreationResponse(json);
        return toJson(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompanyJson updateCompany(Long id, CompanyJson json) {

        CompanyDTO response = companyService.updateCompany(fromJson(json, id));
        return toJson(response);
    }

    public CompanyDTO getCreationResponse(CompanyJson json) {
        log.info("Sending create request to back");
        return companyService.createCompany(fromJson(json));
    }

    public List<CompanyDTO> getListCompaniesResponse() {
        log.info("Sending get Companies List request to back");
        return companyService.getCompaniesList();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or @securityService.hasCompany(#id)")
    public CompanyJson getCompany(Long id) {
        CompanyDTO response = companyService.getCompany(id);
        return toJson(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CompanyJson> getAllCompanies() {
        return
                getListCompaniesResponse().stream()
                        .map(dto -> toJson(dto))
                        .collect(toList());
    }
}
