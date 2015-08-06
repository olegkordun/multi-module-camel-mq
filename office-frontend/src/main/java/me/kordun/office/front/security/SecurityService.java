package me.kordun.office.front.security;

import me.kordun.messaging.dto.UserDTO;
import me.kordun.office.front.service.CompanyFrontService;
import me.kordun.office.front.service.UserFrontService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("securityService")
public class SecurityService {
    private static final Logger log = LoggerFactory.getLogger("SecurityService");
    @Autowired
    private UserFrontService userFrontService;

    public boolean hasCompany(Long companyId) {
        log.info("Check if company {} allowed for user", companyId);
        try {
            UserDTO user = userFrontService.getCurrentUser();
            return companyId.equals(user.getCompanyId());
        } catch (Exception e) {
            log.error("hasCompany ", e);
            return false;
        }
    }
}
