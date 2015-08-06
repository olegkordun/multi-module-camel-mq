package me.kordun.reports;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import me.kordun.enums.CardSystemName;
import me.kordun.enums.TransactionStatus;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.PropertyDefinition;
import org.joda.money.Money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.joda.beans.Bean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

public final class TransactionDetails implements Serializable {
    @JsonProperty("id")
    private final UUID id;
    @JsonProperty("orderId")
    private final UUID orderId;
    @JsonIgnore
    private final OffsetDateTime date;
    @JsonProperty("cardSystem")
    private final CardSystemName cardSystem;
    @JsonProperty("terminalId")
    private final Long terminalId;
    @JsonProperty("status")
    private final TransactionStatus status;
    @JsonProperty("pan")
    private final String pan;
    @JsonProperty("ip")
    private final String ip;
    @JsonProperty("expiry")
    private final String expiry;
    @JsonProperty("cardHolder")
    private final String cardHolder;
    @JsonProperty("eci")
    private final String eci;
    @JsonProperty("xid")
    private final String xid;
    @JsonProperty("md")
    private final String md;
    @JsonProperty("cavv")
    private final String cavv;
    @JsonProperty("approvalCode")
    private final String approvalCode;
    @JsonProperty("refNum")
    private final String refNum;
    @JsonProperty("actionCode")
    private final String actionCode;
    @JsonIgnore
    private final Money amount;
    @JsonProperty("transactionOperations")
    private final List<TransactionOperationRow> transactionOperations;


    @JsonProperty("date")
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount.getAmount();
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return amount.getCurrencyUnit().toCurrency().getCurrencyCode();
    }
    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public CardSystemName getCardSystem() {
        return cardSystem;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getPan() {
        return pan;
    }

    public String getIp() {
        return ip;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getEci() {
        return eci;
    }

    public String getXid() {
        return xid;
    }

    public String getMd() {
        return md;
    }

    public String getCavv() {
        return cavv;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public String getRefNum() {
        return refNum;
    }

    public String getActionCode() {
        return actionCode;
    }

    public List<TransactionOperationRow> getTransactionOperations() {
        return transactionOperations;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private UUID orderId;
        private OffsetDateTime date;
        private CardSystemName cardSystem;
        private Long terminalId;
        private TransactionStatus status;
        private String pan;
        private String ip;
        private String expiry;
        private String cardHolder;
        private String eci;
        private String xid;
        private String md;
        private String cavv;
        private String approvalCode;
        private String refNum;
        private String actionCode;
        private Money amount;
        private List<TransactionOperationRow> transactionOperations;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder orderId(UUID orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder date(OffsetDateTime date) {
            this.date = date;
            return this;
        }

        public Builder cardSystem(CardSystemName cardSystem) {
            this.cardSystem = cardSystem;
            return this;
        }

        public Builder terminalId(Long terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public Builder status(TransactionStatus status) {
            this.status = status;
            return this;
        }

        public Builder pan(String pan) {
            this.pan = pan;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder expiry(String expiry) {
            this.expiry = expiry;
            return this;
        }

        public Builder cardHolder(String cardHolder) {
            this.cardHolder = cardHolder;
            return this;
        }

        public Builder eci(String eci) {
            this.eci = eci;
            return this;
        }

        public Builder xid(String xid) {
            this.xid = xid;
            return this;
        }

        public Builder md(String md) {
            this.md = md;
            return this;
        }

        public Builder cavv(String cavv) {
            this.cavv = cavv;
            return this;
        }

        public Builder approvalCode(String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }

        public Builder refNum(String refNum) {
            this.refNum = refNum;
            return this;
        }

        public Builder actionCode(String actionCode) {
            this.actionCode = actionCode;
            return this;
        }

        public Builder amount(Money amount) {
            this.amount = amount;
            return this;
        }

        public Builder transactionOperations(List<TransactionOperationRow> transactionOperations) {
            this.transactionOperations = transactionOperations;
            return this;
        }

        public TransactionDetails createTransactionDetails() {
            return new TransactionDetails(id, orderId, date, cardSystem, terminalId, status, pan, ip, expiry, cardHolder, eci, xid, md, cavv, approvalCode, refNum, actionCode, amount, transactionOperations);
        }
    }

    public TransactionDetails(UUID id, UUID orderId, OffsetDateTime date, CardSystemName cardSystem, Long terminalId,
                              TransactionStatus status, String pan, String ip, String expiry, String cardHolder,
                              String eci, String xid, String md, String cavv, String approvalCode, String refNum,
                              String actionCode, Money amount, List<TransactionOperationRow> transactionOperations) {
        this.id = id;
        this.orderId = orderId;
        this.date = date;
        this.cardSystem = cardSystem;
        this.terminalId = terminalId;
        this.status = status;
        this.pan = pan;
        this.ip = ip;
        this.expiry = expiry;
        this.cardHolder = cardHolder;
        this.eci = eci;
        this.xid = xid;
        this.md = md;
        this.cavv = cavv;
        this.approvalCode = approvalCode;
        this.refNum = refNum;
        this.actionCode = actionCode;
        this.amount = amount;
        this.transactionOperations = transactionOperations;
    }
}
