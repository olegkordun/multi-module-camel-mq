package me.kordun.persistent.entity;
import me.kordun.enums.CompanyCategory;
import me.kordun.enums.CompanyStatus;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", initialValue = 10000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    private Long id;

    @Column(length = 400, nullable = false)
    private String name;

    @Column
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(length = 4000, nullable = false)
    private String info;

    @Column(length = 4000, nullable = false)
    private String details;

    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyCategory category;

    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public CompanyCategory getCategory() {
        return category;
    }

    public void setCategory(CompanyCategory category) {
        this.category = category;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

}
