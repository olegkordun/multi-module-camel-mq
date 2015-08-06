package me.kordun.office.backend.converter;

import me.kordun.messaging.dto.CompanyDTO;
import me.kordun.persistent.entity.Company;

public class CompanyConverter {
    /**
     * Quick converter implementation. Better use Dozer or something else
     * */
    public static CompanyDTO fromEntity(Company c) {
        return CompanyDTO.builder()
                .id(c.getId())
                .status(c.getStatus())
                .category(c.getCategory())
                .email(c.getEmail())
                .info(c.getInfo())
                .name(c.getName())
                .phone(c.getPhone())
                .details(c.getDetails())
                .build();
    }
}
