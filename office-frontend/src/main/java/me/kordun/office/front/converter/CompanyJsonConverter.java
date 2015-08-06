package me.kordun.office.front.converter;

import me.kordun.json.CompanyJson;
import me.kordun.messaging.dto.CompanyDTO;

import java.util.UUID;

public class CompanyJsonConverter {
    public static CompanyJson toJson(CompanyDTO dto) {
        return CompanyJson.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .status(dto.getStatus())
                .category(dto.getCategory())
                .id(dto.getId())
                .details(dto.getDetails())
                .info(dto.getInfo())
                .createCompanyJson();
    }


    public static CompanyDTO fromJson(CompanyJson json) {
        return fromJson(json, null);
    }

    public static CompanyDTO fromJson(CompanyJson json, Long id) {
        return CompanyDTO.builder()
                .name(json.getName())
                .category(json.getCategory())
                .email(json.getEmail())
                .info(json.getInfo())
                .details(json.getDetails())
                .phone(json.getPhone())
                .id((id != null) ? id : json.getId())
                .status(json.getStatus())
                .build();
    }



}
