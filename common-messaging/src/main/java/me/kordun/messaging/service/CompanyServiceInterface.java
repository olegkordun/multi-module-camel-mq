package me.kordun.messaging.service;

import me.kordun.messaging.dto.CompanyDTO;

import java.util.List;

public interface CompanyServiceInterface {
    CompanyDTO createCompany(CompanyDTO dto);
    CompanyDTO updateCompany(CompanyDTO dto);
    List<CompanyDTO> getCompaniesList();
    CompanyDTO getCompany(Long id);
}
