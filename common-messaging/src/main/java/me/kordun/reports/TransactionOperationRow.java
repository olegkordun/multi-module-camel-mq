package me.kordun.reports;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import me.kordun.enums.TransactionOperationType;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.PropertyDefinition;
import org.joda.money.Money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonRootName("operation")
public final class TransactionOperationRow implements Serializable {

    @JsonProperty("id")
    private final UUID id;
    @JsonProperty("transactionId")
    private final UUID transactionId;
    @JsonIgnore
    private final OffsetDateTime date;
    @JsonIgnore
    private final Money amount;
    @JsonProperty("actionCode")
    private final String actionCode;
    @JsonProperty("refNum")
    private final String refNum;
    @JsonProperty("approvalCode")
    private final String approvalCode;
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    @JsonProperty("type")
    private final TransactionOperationType type;


    @JsonProperty("date")
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount.getAmount();
    }

    @JsonProperty("currency")
    public String getCurrency(){
        return amount.getCurrencyUnit().toCurrency().getCurrencyCode();
    }
    
    public UUID getId() {
        return id;
    }

    
    public UUID getTransactionId() {
        return transactionId;
    }

   
    
    public String getActionCode() {
        return actionCode;
    }

    
    public String getRefNum() {
        return refNum;
    }

    
    public String getApprovalCode() {
        return approvalCode;
    }

    
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    
    public TransactionOperationType getType() {
        return type;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private UUID id;
        private UUID transactionId;
        private OffsetDateTime date;
        private Money amount;
        private String actionCode;
        private String refNum;
        private String approvalCode;
        private Boolean isSuccess;
        private TransactionOperationType type;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder transactionId(UUID transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder date(OffsetDateTime date) {
            this.date = date;
            return this;
        }

        public Builder amount(Money amount) {
            this.amount = amount;
            return this;
        }

        public Builder actionCode(String actionCode) {
            this.actionCode = actionCode;
            return this;
        }

        public Builder refNum(String refNum) {
            this.refNum = refNum;
            return this;
        }

        public Builder approvalCode(String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }

        public Builder isSuccess(Boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public Builder type(TransactionOperationType type) {
            this.type = type;
            return this;
        }

        public TransactionOperationRow createTransactionOperationRow() {
            return new TransactionOperationRow(id, transactionId, date, amount, actionCode, refNum, approvalCode, isSuccess, type);
        }
    }
    public TransactionOperationRow(UUID id, UUID transactionId, OffsetDateTime date, Money amount, String actionCode,
                                   String refNum, String approvalCode, Boolean isSuccess, TransactionOperationType type) {
        this.id = id;
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.actionCode = actionCode;
        this.refNum = refNum;
        this.approvalCode = approvalCode;
        this.isSuccess = isSuccess;
        this.type = type;
    }
    
    
}
