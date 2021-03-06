package me.kordun.messaging.dto;

import com.google.common.collect.ImmutableSet;
import me.kordun.enums.Role;
import me.kordun.json.UserJson;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.PropertyDefinition;

import java.io.Serializable;
import java.util.*;

import org.joda.beans.Bean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@BeanDefinition
public final class UserDTO implements ImmutableBean, Serializable {
    @PropertyDefinition
    private final Long id;
    @PropertyDefinition
    private final String email;
    @PropertyDefinition
    private final String firstName;
    @PropertyDefinition
    private final String userName;
    @PropertyDefinition
    private final String lastName;
    @PropertyDefinition
    private final String comment;
    @PropertyDefinition
    private final String passwordDigest;
    @PropertyDefinition
    private final Set<Role> roles;
    @PropertyDefinition
    private final Long companyId;

    public UserJson toJson() {
        UserJson u = new UserJson();
        u.setId(getId());
        u.setEmail(getEmail());
        u.setLastName(getLastName());
        u.setComment(getComment());
        u.setCompanyId(getCompanyId());
        u.setFirstName(getFirstName());
        u.setUserName(getUserName());
        u.setRoles(new HashSet<>(getRoles()));
        return u;
    }

    public static UserDTO fromJson(UserJson u) {
        if(u == null) return null;
        UserDTO dto = UserDTO.builder()
                .id(u.getId())
                .comment(u.getComment())
                .email(u.getEmail())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .roles(u.getRoles() != null ? new HashSet<>(u.getRoles()) : null)
                .companyId(u.getCompanyId())
                .userName(u.getUserName())
                .passwordDigest(u.getPasswordDigest())
                .build();

        return dto;
    }
    //------------------------- AUTOGENERATED START -------------------------
    ///CLOVER:OFF
    /**
     * The meta-bean for {@code UserDTO}.
     * @return the meta-bean, not null
     */
    public static UserDTO.Meta meta() {
        return UserDTO.Meta.INSTANCE;
    }

    static {
        JodaBeanUtils.registerMetaBean(UserDTO.Meta.INSTANCE);
    }

    /**
     * The serialization version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Returns a builder used to create an instance of the bean.
     * @return the builder, not null
     */
    public static UserDTO.Builder builder() {
        return new UserDTO.Builder();
    }

    private UserDTO(
            Long id,
            String email,
            String firstName,
            String userName,
            String lastName,
            String comment,
            String passwordDigest,
            Set<Role> roles,
            Long companyId) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
        this.comment = comment;
        this.passwordDigest = passwordDigest;
        this.roles = (roles != null ? ImmutableSet.copyOf(roles) : null);
        this.companyId = companyId;
    }

    @Override
    public UserDTO.Meta metaBean() {
        return UserDTO.Meta.INSTANCE;
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
     * Gets the email.
     * @return the value of the property
     */
    public String getEmail() {
        return email;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the firstName.
     * @return the value of the property
     */
    public String getFirstName() {
        return firstName;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the userName.
     * @return the value of the property
     */
    public String getUserName() {
        return userName;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the lastName.
     * @return the value of the property
     */
    public String getLastName() {
        return lastName;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the comment.
     * @return the value of the property
     */
    public String getComment() {
        return comment;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the passwordDigest.
     * @return the value of the property
     */
    public String getPasswordDigest() {
        return passwordDigest;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the roles.
     * @return the value of the property
     */
    public Set<Role> getRoles() {
        return roles;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the companyId.
     * @return the value of the property
     */
    public Long getCompanyId() {
        return companyId;
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
            UserDTO other = (UserDTO) obj;
            return JodaBeanUtils.equal(getId(), other.getId()) &&
                    JodaBeanUtils.equal(getEmail(), other.getEmail()) &&
                    JodaBeanUtils.equal(getFirstName(), other.getFirstName()) &&
                    JodaBeanUtils.equal(getUserName(), other.getUserName()) &&
                    JodaBeanUtils.equal(getLastName(), other.getLastName()) &&
                    JodaBeanUtils.equal(getComment(), other.getComment()) &&
                    JodaBeanUtils.equal(getPasswordDigest(), other.getPasswordDigest()) &&
                    JodaBeanUtils.equal(getRoles(), other.getRoles()) &&
                    JodaBeanUtils.equal(getCompanyId(), other.getCompanyId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = getClass().hashCode();
        hash = hash * 31 + JodaBeanUtils.hashCode(getId());
        hash = hash * 31 + JodaBeanUtils.hashCode(getEmail());
        hash = hash * 31 + JodaBeanUtils.hashCode(getFirstName());
        hash = hash * 31 + JodaBeanUtils.hashCode(getUserName());
        hash = hash * 31 + JodaBeanUtils.hashCode(getLastName());
        hash = hash * 31 + JodaBeanUtils.hashCode(getComment());
        hash = hash * 31 + JodaBeanUtils.hashCode(getPasswordDigest());
        hash = hash * 31 + JodaBeanUtils.hashCode(getRoles());
        hash = hash * 31 + JodaBeanUtils.hashCode(getCompanyId());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(320);
        buf.append("UserDTO{");
        buf.append("id").append('=').append(getId()).append(',').append(' ');
        buf.append("email").append('=').append(getEmail()).append(',').append(' ');
        buf.append("firstName").append('=').append(getFirstName()).append(',').append(' ');
        buf.append("userName").append('=').append(getUserName()).append(',').append(' ');
        buf.append("lastName").append('=').append(getLastName()).append(',').append(' ');
        buf.append("comment").append('=').append(getComment()).append(',').append(' ');
        buf.append("passwordDigest").append('=').append(getPasswordDigest()).append(',').append(' ');
        buf.append("roles").append('=').append(getRoles()).append(',').append(' ');
        buf.append("companyId").append('=').append(JodaBeanUtils.toString(getCompanyId()));
        buf.append('}');
        return buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-bean for {@code UserDTO}.
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
                this, "id", UserDTO.class, Long.class);
        /**
         * The meta-property for the {@code email} property.
         */
        private final MetaProperty<String> email = DirectMetaProperty.ofImmutable(
                this, "email", UserDTO.class, String.class);
        /**
         * The meta-property for the {@code firstName} property.
         */
        private final MetaProperty<String> firstName = DirectMetaProperty.ofImmutable(
                this, "firstName", UserDTO.class, String.class);
        /**
         * The meta-property for the {@code userName} property.
         */
        private final MetaProperty<String> userName = DirectMetaProperty.ofImmutable(
                this, "userName", UserDTO.class, String.class);
        /**
         * The meta-property for the {@code lastName} property.
         */
        private final MetaProperty<String> lastName = DirectMetaProperty.ofImmutable(
                this, "lastName", UserDTO.class, String.class);
        /**
         * The meta-property for the {@code comment} property.
         */
        private final MetaProperty<String> comment = DirectMetaProperty.ofImmutable(
                this, "comment", UserDTO.class, String.class);
        /**
         * The meta-property for the {@code passwordDigest} property.
         */
        private final MetaProperty<String> passwordDigest = DirectMetaProperty.ofImmutable(
                this, "passwordDigest", UserDTO.class, String.class);
        /**
         * The meta-property for the {@code roles} property.
         */
        @SuppressWarnings({"unchecked", "rawtypes" })
        private final MetaProperty<Set<Role>> roles = DirectMetaProperty.ofImmutable(
                this, "roles", UserDTO.class, (Class) Set.class);
        /**
         * The meta-property for the {@code companyId} property.
         */
        private final MetaProperty<Long> companyId = DirectMetaProperty.ofImmutable(
                this, "companyId", UserDTO.class, Long.class);
        /**
         * The meta-properties.
         */
        private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
                this, null,
                "id",
                "email",
                "firstName",
                "userName",
                "lastName",
                "comment",
                "passwordDigest",
                "roles",
                "companyId");

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
                case 96619420:  // email
                    return email;
                case 132835675:  // firstName
                    return firstName;
                case -266666762:  // userName
                    return userName;
                case -1459599807:  // lastName
                    return lastName;
                case 950398559:  // comment
                    return comment;
                case -969038785:  // passwordDigest
                    return passwordDigest;
                case 108695229:  // roles
                    return roles;
                case -1412818312:  // companyId
                    return companyId;
            }
            return super.metaPropertyGet(propertyName);
        }

        @Override
        public UserDTO.Builder builder() {
            return new UserDTO.Builder();
        }

        @Override
        public Class<? extends UserDTO> beanType() {
            return UserDTO.class;
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
         * The meta-property for the {@code email} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> email() {
            return email;
        }

        /**
         * The meta-property for the {@code firstName} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> firstName() {
            return firstName;
        }

        /**
         * The meta-property for the {@code userName} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> userName() {
            return userName;
        }

        /**
         * The meta-property for the {@code lastName} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> lastName() {
            return lastName;
        }

        /**
         * The meta-property for the {@code comment} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> comment() {
            return comment;
        }

        /**
         * The meta-property for the {@code passwordDigest} property.
         * @return the meta-property, not null
         */
        public MetaProperty<String> passwordDigest() {
            return passwordDigest;
        }

        /**
         * The meta-property for the {@code roles} property.
         * @return the meta-property, not null
         */
        public MetaProperty<Set<Role>> roles() {
            return roles;
        }

        /**
         * The meta-property for the {@code companyId} property.
         * @return the meta-property, not null
         */
        public MetaProperty<Long> companyId() {
            return companyId;
        }

        //-----------------------------------------------------------------------
        @Override
        protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
            switch (propertyName.hashCode()) {
                case 3355:  // id
                    return ((UserDTO) bean).getId();
                case 96619420:  // email
                    return ((UserDTO) bean).getEmail();
                case 132835675:  // firstName
                    return ((UserDTO) bean).getFirstName();
                case -266666762:  // userName
                    return ((UserDTO) bean).getUserName();
                case -1459599807:  // lastName
                    return ((UserDTO) bean).getLastName();
                case 950398559:  // comment
                    return ((UserDTO) bean).getComment();
                case -969038785:  // passwordDigest
                    return ((UserDTO) bean).getPasswordDigest();
                case 108695229:  // roles
                    return ((UserDTO) bean).getRoles();
                case -1412818312:  // companyId
                    return ((UserDTO) bean).getCompanyId();
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
     * The bean-builder for {@code UserDTO}.
     */
    public static final class Builder extends DirectFieldsBeanBuilder<UserDTO> {

        private Long id;
        private String email;
        private String firstName;
        private String userName;
        private String lastName;
        private String comment;
        private String passwordDigest;
        private Set<Role> roles;
        private Long companyId;

        /**
         * Restricted constructor.
         */
        private Builder() {
        }

        /**
         * Restricted copy constructor.
         * @param beanToCopy  the bean to copy from, not null
         */
        private Builder(UserDTO beanToCopy) {
            this.id = beanToCopy.getId();
            this.email = beanToCopy.getEmail();
            this.firstName = beanToCopy.getFirstName();
            this.userName = beanToCopy.getUserName();
            this.lastName = beanToCopy.getLastName();
            this.comment = beanToCopy.getComment();
            this.passwordDigest = beanToCopy.getPasswordDigest();
            this.roles = (beanToCopy.getRoles() != null ? ImmutableSet.copyOf(beanToCopy.getRoles()) : null);
            this.companyId = beanToCopy.getCompanyId();
        }

        //-----------------------------------------------------------------------
        @Override
        public Object get(String propertyName) {
            switch (propertyName.hashCode()) {
                case 3355:  // id
                    return id;
                case 96619420:  // email
                    return email;
                case 132835675:  // firstName
                    return firstName;
                case -266666762:  // userName
                    return userName;
                case -1459599807:  // lastName
                    return lastName;
                case 950398559:  // comment
                    return comment;
                case -969038785:  // passwordDigest
                    return passwordDigest;
                case 108695229:  // roles
                    return roles;
                case -1412818312:  // companyId
                    return companyId;
                default:
                    throw new NoSuchElementException("Unknown property: " + propertyName);
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public Builder set(String propertyName, Object newValue) {
            switch (propertyName.hashCode()) {
                case 3355:  // id
                    this.id = (Long) newValue;
                    break;
                case 96619420:  // email
                    this.email = (String) newValue;
                    break;
                case 132835675:  // firstName
                    this.firstName = (String) newValue;
                    break;
                case -266666762:  // userName
                    this.userName = (String) newValue;
                    break;
                case -1459599807:  // lastName
                    this.lastName = (String) newValue;
                    break;
                case 950398559:  // comment
                    this.comment = (String) newValue;
                    break;
                case -969038785:  // passwordDigest
                    this.passwordDigest = (String) newValue;
                    break;
                case 108695229:  // roles
                    this.roles = (Set<Role>) newValue;
                    break;
                case -1412818312:  // companyId
                    this.companyId = (Long) newValue;
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
        public UserDTO build() {
            return new UserDTO(
                    id,
                    email,
                    firstName,
                    userName,
                    lastName,
                    comment,
                    passwordDigest,
                    roles,
                    companyId);
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
         * Sets the {@code email} property in the builder.
         * @param email  the new value
         * @return this, for chaining, not null
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the {@code firstName} property in the builder.
         * @param firstName  the new value
         * @return this, for chaining, not null
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the {@code userName} property in the builder.
         * @param userName  the new value
         * @return this, for chaining, not null
         */
        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Sets the {@code lastName} property in the builder.
         * @param lastName  the new value
         * @return this, for chaining, not null
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets the {@code comment} property in the builder.
         * @param comment  the new value
         * @return this, for chaining, not null
         */
        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        /**
         * Sets the {@code passwordDigest} property in the builder.
         * @param passwordDigest  the new value
         * @return this, for chaining, not null
         */
        public Builder passwordDigest(String passwordDigest) {
            this.passwordDigest = passwordDigest;
            return this;
        }

        /**
         * Sets the {@code roles} property in the builder.
         * @param roles  the new value
         * @return this, for chaining, not null
         */
        public Builder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        /**
         * Sets the {@code roles} property in the builder
         * from an array of objects.
         * @param roles  the new value
         * @return this, for chaining, not null
         */
        public Builder roles(Role... roles) {
            return roles(ImmutableSet.copyOf(roles));
        }

        /**
         * Sets the {@code companyId} property in the builder.
         * @param companyId  the new value
         * @return this, for chaining, not null
         */
        public Builder companyId(Long companyId) {
            this.companyId = companyId;
            return this;
        }

        //-----------------------------------------------------------------------
        @Override
        public String toString() {
            StringBuilder buf = new StringBuilder(320);
            buf.append("UserDTO.Builder{");
            buf.append("id").append('=').append(JodaBeanUtils.toString(id)).append(',').append(' ');
            buf.append("email").append('=').append(JodaBeanUtils.toString(email)).append(',').append(' ');
            buf.append("firstName").append('=').append(JodaBeanUtils.toString(firstName)).append(',').append(' ');
            buf.append("userName").append('=').append(JodaBeanUtils.toString(userName)).append(',').append(' ');
            buf.append("lastName").append('=').append(JodaBeanUtils.toString(lastName)).append(',').append(' ');
            buf.append("comment").append('=').append(JodaBeanUtils.toString(comment)).append(',').append(' ');
            buf.append("passwordDigest").append('=').append(JodaBeanUtils.toString(passwordDigest)).append(',').append(' ');
            buf.append("roles").append('=').append(JodaBeanUtils.toString(roles)).append(',').append(' ');
            buf.append("companyId").append('=').append(JodaBeanUtils.toString(companyId));
            buf.append('}');
            return buf.toString();
        }

    }

    ///CLOVER:ON
    //-------------------------- AUTOGENERATED END --------------------------
}
