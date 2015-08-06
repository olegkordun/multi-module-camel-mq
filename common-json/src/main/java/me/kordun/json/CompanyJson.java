package me.kordun.json;

import com.fasterxml.jackson.annotation.*;
import me.kordun.enums.CompanyCategory;
import me.kordun.enums.CompanyStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonRootName("company")
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CompanyJson {
    @JsonProperty(value = "id", required = true)
    protected Long id;
    @JsonProperty(value = "status", required = true)
    protected CompanyStatus status;

    @JsonProperty(value = "name", required = true)
    @NotNull(message = "company name must be filled")
    @Size(min = 1, max = 400, message = "Name must be filled and less than 400 symbols")
    private String name;
    @JsonProperty(value = "phone", required = false)
    private String phone;

    @JsonProperty(value = "email", required = true)
    @Pattern(regexp = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b", message = "Enter correct email")
    private String email;

    @JsonProperty(value = "info", required = true)
    @Size(min = 1, max = 4000, message = "info must be filled and less than 4000 symbols")
    @NotNull(message = "info must be filled")
    private String info;

    @Size(min = 1, max = 4000, message = "details must be filled and less than 4000 symbols")
    @NotNull(message = "details must be filled")
    @JsonProperty(value = "details", required = true)
    private String details;
    @NotNull(message = "Enter category")
    @JsonProperty(value = "category", required = true)
    private CompanyCategory category;

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private Long id;
        private CompanyStatus status;
        private String name;
        private String phone;
        private String email;
        private String info;
        private String details;
        private CompanyCategory category;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder status(CompanyStatus status) {
            this.status = status;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder category(CompanyCategory category) {
            this.category = category;
            return this;
        }


        public CompanyJson createCompanyJson() {
            return new CompanyJson(id, status, name, phone, email, info, details, category);
        }
    }

    @JsonCreator
    public CompanyJson(
            @JsonProperty("id") Long id,
            @JsonProperty("status") CompanyStatus status,
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("info") String info,
            @JsonProperty("details") String details,
            @JsonProperty("category") CompanyCategory category
    ) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.info = info;
        this.details = details;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getInfo() {
        return info;
    }

    public String getDetails() {
        return details;
    }

    public CompanyCategory getCategory() {
        return category;
    }

}
