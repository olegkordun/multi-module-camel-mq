package me.kordun.messaging.dto;


import me.kordun.enums.CompanyCategory;
import me.kordun.enums.CompanyStatus;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.PropertyDefinition;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import org.joda.beans.Bean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

@BeanDefinition
public final class CompanyDTO implements ImmutableBean, Serializable {
    @PropertyDefinition
    private final Long id;
    @PropertyDefinition
    private final String name;
    @PropertyDefinition
    private final String phone;
    @PropertyDefinition
    private final String email;
    @PropertyDefinition
    private final String info;
    @PropertyDefinition
    private final String details;
    @PropertyDefinition
    private final CompanyCategory category;
    @PropertyDefinition
    private final CompanyStatus status;


    //------------------------- AUTOGENERATED START -------------------------
    ///CLOVER:OFF
    /**
     * The meta-bean for {@code CompanyDTO}.
     * @return the meta-bean, not null
     */
    public static CompanyDTO.Meta meta() {
        return CompanyDTO.Meta.INSTANCE;
    }

    static {
        JodaBeanUtils.registerMetaBean(CompanyDTO.Meta.INSTANCE);
    }

    /**
     * The serialization version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Returns a builder used to create an instance of the bean.
     * @return the builder, not null
     */
    public static CompanyDTO.Builder builder() {
        return new CompanyDTO.Builder();
    }

    private CompanyDTO(
            Long id,
            String name,
            String phone,
            String email,
            String info,
            String details,
            CompanyCategory category,
            CompanyStatus status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.info = info;
        this.details = details;
        this.category = category;
        this.status = status;
    }

    @Override
    public CompanyDTO.Meta metaBean() {
        return CompanyDTO.Meta.INSTANCE;
    }

    @Override
    public <R> Property<R> property(String propertyName) {
        return metaBean().<R>metaProperty(propertyName).createProperty(this);
    }

    @Override
    public Set<String> propertyNames() {
        return metaBean().metaPropertyMap().keySet();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the id.
     * @return the value of the property
     */
    public Long getId() {
        return id;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the name.
     * @return the value of the property
     */
    public String getName() {
        return name;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the phone.
     * @return the value of the property
     */
    public String getPhone() {
        return phone;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the email.
     * @return the value of the property
     */
    public String getEmail() {
        return email;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the info.
     * @return the value of the property
     */
    public String getInfo() {
        return info;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the details.
     * @return the value of the property
     */
    public String getDetails() {
        return details;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the category.
     * @return the value of the property
     */
    public CompanyCategory getCategory() {
        return category;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the status.
     * @return the value of the property
     */
    public CompanyStatus getStatus() {
        return status;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a builder that allows this bean to be mutated.
     * @return the mutable builder, not null
     */
    public Builder toBuilder() {
        return new Builder(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == this.getClass()) {
            CompanyDTO other = (CompanyDTO) obj;
            return JodaBeanUtils.equal(getId(), other.getId()) &&
                    JodaBeanUtils.equal(getName(), other.getName()) &&
                    JodaBeanUtils.equal(getPhone(), other.getPhone()) &&
                    JodaBeanUtils.equal(getEmail(), other.getEmail()) &&
                    JodaBeanUtils.equal(getInfo(), other.getInfo()) &&
                    JodaBeanUtils.equal(getDetails(), other.getDetails()) &&
                    JodaBeanUtils.equal(getCategory(), other.getCategory()) &&
                    JodaBeanUtils.equal(getStatus(), other.getStatus());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = getClass().hashCode();
        hash = hash * 31 + JodaBeanUtils.hashCode(getId());
        hash = hash * 31 + JodaBeanUtils.hashCode(getName());
        hash = hash * 31 + JodaBeanUtils.hashCode(getPhone());
        hash = hash * 31 + JodaBeanUtils.hashCode(getEmail());
        hash = hash * 31 + JodaBeanUtils.hashCode(getInfo());
        hash = hash * 31 + JodaBeanUtils.hashCode(getDetails());
        hash = hash * 31 + JodaBeanUtils.hashCode(getCategory());
        hash = hash * 31 + JodaBeanUtils.hashCode(getStatus());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(288);
        buf.append("CompanyDTO{");
        buf.append("id").append('=').append(getId()).append(',').append(' ');
        buf.append("name").append('=').append(getName()).append(',').append(' ');
        buf.append("phone").append('=').append(getPhone()).append(',').append(' ');
        buf.append("email").append('=').append(getEmail()).append(',').append(' ');
        buf.append("info").append('=').append(getInfo()).append(',').append(' ');
        buf.append("details").append('=').append(getDetails()).append(',').append(' ');
        buf.append("category").append('=').append(getCategory()).append(',').append(' ');
        buf.append("status").append('=').append(JodaBeanUtils.toString(getStatus()));
        buf.append('}');
        return buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-bean for {@code CompanyDTO}.
     */
    public static final class Meta extends DirectMetaBean {
        /**
         * The singleton instance of the meta-bean.
         */
        static final Meta INSTANCE = new Meta();

        /**
         * The meta-property for the {@code id} property.
         */
        private final MetaProperty<Long> id = DirectMetaProperty.ofImmutable(
                this, "id", CompanyDTO.class, Long.class);
        /**
         * The meta-property for the {@code name} property.
         */
        private final MetaProperty<String> name = DirectMetaProperty.ofImmutable(
                this, "name", CompanyDTO.class, String.class);
        /**
         * The meta-property for the {@code phone} property.
         */
        private final MetaProperty<String> phone = DirectMetaProperty.ofImmutable(
                this, "phone", CompanyDTO.class, String.class);
        /**
         * The meta-property for the {@code email} property.
         */
        private final MetaProperty<String> email = DirectMetaProperty.ofImmutable(
                this, "email", CompanyDTO.class, String.class);
        /**
         * The meta-property for the {@code info} property.
         */
        private final MetaProperty<String> info = DirectMetaProperty.ofImmutable(
                this, "info", CompanyDTO.class, String.class);
        /**
         * The meta-property for the {@code details} property.
         */
        private final MetaProperty<String> details = DirectMetaProperty.ofImmutable(
                this, "details", CompanyDTO.class, String.class);
        /**
         * The meta-property for the {@code category} property.
         */
        private final MetaProperty<CompanyCategory> category = DirectMetaProperty.ofImmutable(
                this, "category", CompanyDTO.class, CompanyCategory.class);
        /**
         * The meta-property for the {@code status} property.
         */
        private final MetaProperty<CompanyStatus> status = DirectMetaProperty.ofImmutable(
                this, "status", CompanyDTO.class, CompanyStatus.class);
        /**
         * The meta-properties.
         */
        private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
                this, null,
                "id",
                "name",
                "phone",
                "email",
                "info",
                "details",
                "category",
                "status");

        /**
         * Restricted constructor.
         */
        private Meta() {
        }

        @Override
        protected MetaProperty<?> metaPropertyGet(String propertyName) {
            switch (propertyName.hashCode()) {
                case 3355:  // id
                    return id;
                case 3373707:  // name
                    return name;
                case 106642798:  // phone
                    return phone;
                case 96619420:  // email
                    return email;
                case 3237038:  // info
                    return info;
                case 1557721666:  // details
                    return details;
                case 50511102:  // category
                    return category;
                case -892481550:  // status
                    return status;
            }
            return super.metaPropertyGet(propertyName);
        }

        @Override
        public CompanyDTO.Builder builder() {
            return new CompanyDTO.Builder();
        }

        @Override
        public Class<? extends CompanyDTO> beanType() {
            return CompanyDTO.class;
        }

        @Override
        public Map<String, MetaProperty<?>> metaPropertyMap() {
            return metaPropertyMap$;
        }

        //-----------------------------------------------------------------------
        /**
         * The meta-property for the {@code id} property.
         * @return the meta-property, not null
         */
        public MetaProperty<Long> id() {
            return id;
        }

        /**
         * The meta-property for the {@code name} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> name() {
            return name;
        }

        /**
         * The meta-property for the {@code phone} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> phone() {
            return phone;
        }

        /**
         * The meta-property for the {@code email} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> email() {
            return email;
        }

        /**
         * The meta-property for the {@code info} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> info() {
            return info;
        }

        /**
         * The meta-property for the {@code details} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> details() {
            return details;
        }

        /**
         * The meta-property for the {@code category} property.
         * @return the meta-property, not null
         */
        public MetaProperty<CompanyCategory> category() {
            return category;
        }

        /**
         * The meta-property for the {@code status} property.
         * @return the meta-property, not null
         */
        public MetaProperty<CompanyStatus> status() {
            return status;
        }

        //-----------------------------------------------------------------------
        @Override
        protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
            switch (propertyName.hashCode()) {
                case 3355:  // id
                    return ((CompanyDTO) bean).getId();
                case 3373707:  // name
                    return ((CompanyDTO) bean).getName();
                case 106642798:  // phone
                    return ((CompanyDTO) bean).getPhone();
                case 96619420:  // email
                    return ((CompanyDTO) bean).getEmail();
                case 3237038:  // info
                    return ((CompanyDTO) bean).getInfo();
                case 1557721666:  // details
                    return ((CompanyDTO) bean).getDetails();
                case 50511102:  // category
                    return ((CompanyDTO) bean).getCategory();
                case -892481550:  // status
                    return ((CompanyDTO) bean).getStatus();
            }
            return super.propertyGet(bean, propertyName, quiet);
        }

        @Override
        protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
            metaProperty(propertyName);
            if (quiet) {
                return;
            }
            throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
        }

    }

    //-----------------------------------------------------------------------
    /**
     * The bean-builder for {@code CompanyDTO}.
     */
    public static final class Builder extends DirectFieldsBeanBuilder<CompanyDTO> {

        private Long id;
        private String name;
        private String phone;
        private String email;
        private String info;
        private String details;
        private CompanyCategory category;
        private CompanyStatus status;

        /**
         * Restricted constructor.
         */
        private Builder() {
        }

        /**
         * Restricted copy constructor.
         * @param beanToCopy  the bean to copy from, not null
         */
        private Builder(CompanyDTO beanToCopy) {
            this.id = beanToCopy.getId();
            this.name = beanToCopy.getName();
            this.phone = beanToCopy.getPhone();
            this.email = beanToCopy.getEmail();
            this.info = beanToCopy.getInfo();
            this.details = beanToCopy.getDetails();
            this.category = beanToCopy.getCategory();
            this.status = beanToCopy.getStatus();
        }

        //-----------------------------------------------------------------------
        @Override
        public Object get(String propertyName) {
            switch (propertyName.hashCode()) {
                case 3355:  // id
                    return id;
                case 3373707:  // name
                    return name;
                case 106642798:  // phone
                    return phone;
                case 96619420:  // email
                    return email;
                case 3237038:  // info
                    return info;
                case 1557721666:  // details
                    return details;
                case 50511102:  // category
                    return category;
                case -892481550:  // status
                    return status;
                default:
                    throw new NoSuchElementException("Unknown property: " + propertyName);
            }
        }

        @Override
        public Builder set(String propertyName, Object newValue) {
            switch (propertyName.hashCode()) {
                case 3355:  // id
                    this.id = (Long) newValue;
                    break;
                case 3373707:  // name
                    this.name = (String) newValue;
                    break;
                case 106642798:  // phone
                    this.phone = (String) newValue;
                    break;
                case 96619420:  // email
                    this.email = (String) newValue;
                    break;
                case 3237038:  // info
                    this.info = (String) newValue;
                    break;
                case 1557721666:  // details
                    this.details = (String) newValue;
                    break;
                case 50511102:  // category
                    this.category = (CompanyCategory) newValue;
                    break;
                case -892481550:  // status
                    this.status = (CompanyStatus) newValue;
                    break;
                default:
                    throw new NoSuchElementException("Unknown property: " + propertyName);
            }
            return this;
        }

        @Override
        public Builder set(MetaProperty<?> property, Object value) {
            super.set(property, value);
            return this;
        }

        @Override
        public Builder setString(String propertyName, String value) {
            setString(meta().metaProperty(propertyName), value);
            return this;
        }

        @Override
        public Builder setString(MetaProperty<?> property, String value) {
            super.setString(property, value);
            return this;
        }

        @Override
        public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
            super.setAll(propertyValueMap);
            return this;
        }

        @Override
        public CompanyDTO build() {
            return new CompanyDTO(
                    id,
                    name,
                    phone,
                    email,
                    info,
                    details,
                    category,
                    status);
        }

        //-----------------------------------------------------------------------
        /**
         * Sets the {@code id} property in the builder.
         * @param id  the new value
         * @return this, for chaining, not null
         */
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the {@code name} property in the builder.
         * @param name  the new value
         * @return this, for chaining, not null
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code phone} property in the builder.
         * @param phone  the new value
         * @return this, for chaining, not null
         */
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * Sets the {@code email} property in the builder.
         * @param email  the new value
         * @return this, for chaining, not null
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the {@code info} property in the builder.
         * @param info  the new value
         * @return this, for chaining, not null
         */
        public Builder info(String info) {
            this.info = info;
            return this;
        }

        /**
         * Sets the {@code details} property in the builder.
         * @param details  the new value
         * @return this, for chaining, not null
         */
        public Builder details(String details) {
            this.details = details;
            return this;
        }

        /**
         * Sets the {@code category} property in the builder.
         * @param category  the new value
         * @return this, for chaining, not null
         */
        public Builder category(CompanyCategory category) {
            this.category = category;
            return this;
        }

        /**
         * Sets the {@code status} property in the builder.
         * @param status  the new value
         * @return this, for chaining, not null
         */
        public Builder status(CompanyStatus status) {
            this.status = status;
            return this;
        }

        //-----------------------------------------------------------------------
        @Override
        public String toString() {
            StringBuilder buf = new StringBuilder(288);
            buf.append("CompanyDTO.Builder{");
            buf.append("id").append('=').append(JodaBeanUtils.toString(id)).append(',').append(' ');
            buf.append("name").append('=').append(JodaBeanUtils.toString(name)).append(',').append(' ');
            buf.append("phone").append('=').append(JodaBeanUtils.toString(phone)).append(',').append(' ');
            buf.append("email").append('=').append(JodaBeanUtils.toString(email)).append(',').append(' ');
            buf.append("info").append('=').append(JodaBeanUtils.toString(info)).append(',').append(' ');
            buf.append("details").append('=').append(JodaBeanUtils.toString(details)).append(',').append(' ');
            buf.append("category").append('=').append(JodaBeanUtils.toString(category)).append(',').append(' ');
            buf.append("status").append('=').append(JodaBeanUtils.toString(status));
            buf.append('}');
            return buf.toString();
        }

    }

    ///CLOVER:ON
    //-------------------------- AUTOGENERATED END --------------------------
}
