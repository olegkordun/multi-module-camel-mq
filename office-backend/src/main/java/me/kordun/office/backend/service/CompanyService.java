package me.kordun.office.backend.service;

import me.kordun.enums.CompanyStatus;
import me.kordun.messaging.dto.CompanyDTO;
import me.kordun.messaging.service.CompanyServiceInterface;
import me.kordun.office.backend.camel.BeanOfficeBackendNames;
import me.kordun.office.backend.converter.CompanyConverter;
import me.kordun.persistent.entity.Company;
import me.kordun.persistent.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static me.kordun.office.backend.converter.CompanyConverter.fromEntity;
import static java.util.stream.Collectors.toList;

@Service(value = BeanOfficeBackendNames.COMPANY_SERVICE)
public class CompanyService implements CompanyServiceInterface {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDTO createCompany(CompanyDTO dto) {
        Company c = getCompany(dto);
        c.setStatus(CompanyStatus.ACTIVE);

        c = companyRepository.save(c);
        return fromEntity(c);
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO dto) {
        Company c = getCompany(dto);
        c = companyRepository.save(c);
        return fromEntity(c);
    }

    @Override
    public List<CompanyDTO> getCompaniesList() {
        return StreamSupport.stream(companyRepository.findAll().spliterator(), false)
                .map(CompanyConverter::fromEntity).collect(toList());
    }

    @Override
    public CompanyDTO getCompany(Long id) {
        Company c = companyRepository.findOne(id);
        return fromEntity(c);
    }

    /**
     * Quick converter implementation. Better use Dozer or something else
     * */
    private Company getCompany(CompanyDTO dto) {
        Company c = new Company();
        c.setId(dto.getId());
        c.setCategory(dto.getCategory());
        c.setDetails(dto.getDetails());
        c.setEmail(dto.getEmail());
        c.setInfo(dto.getInfo());
        c.setName(dto.getName());
        c.setStatus(dto.getStatus());
        c.setPhone(dto.getPhone());
        return c;
    }


}
