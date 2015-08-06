package me.kordun.reports;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.kordun.enums.CardSystemName;
import me.kordun.enums.Currency;
import me.kordun.enums.TransactionOperationType;
import me.kordun.enums.TransactionStatus;
import org.joda.money.Money;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@JsonRootName("transaction")
public class TransactionRow implements Serializable {

    @JsonProperty("id")
    private final UUID id;
    @JsonIgnore
    private final OffsetDateTime date;
    @JsonProperty("cardSystem")
    private final CardSystemName cardSystem;
    @JsonProperty("pan")
    private final String pan;
    @JsonProperty("is3D")
    private final Boolean is3D;
    @JsonProperty("cardHolder")
    private final String cardHolder;
    @JsonIgnore
    private final Money amount;
    @JsonProperty("status")
    private final TransactionStatus status;


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

    public CardSystemName getCardSystem() {
        return cardSystem;
    }

    public String getPan() {
        return pan;
    }


    public Boolean getIs3D() {
        return is3D;
    }

    public String getCardHolder() {
        return cardHolder;
    }


    public TransactionStatus getStatus() {
        return status;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UUID id;
        private OffsetDateTime date;
        private CardSystemName cardSystem;
        private String pan;
        private Boolean is3D;
        private String cardHolder;
        private Money amount;
        private TransactionStatus status;

        public Builder id(UUID id) {
            this.id = id;
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

        public Builder pan(String pan) {
            this.pan = pan;
            return this;
        }

        public Builder is3D(Boolean is3D) {
            this.is3D = is3D;
            return this;
        }

        public Builder cardHolder(String cardHolder) {
            this.cardHolder = cardHolder;
            return this;
        }

        public Builder amount(Money amount) {
            this.amount = amount;
            return this;
        }

        public Builder status(TransactionStatus status) {
            this.status = status;
            return this;
        }

        public TransactionRow createTransactionRow() {
            return new TransactionRow(id, date, cardSystem, pan, is3D, cardHolder, amount, status);
        }
    }

    public TransactionRow(UUID id, OffsetDateTime date, CardSystemName cardSystem, String pan,
                          Boolean is3D, String cardHolder, Money amount, TransactionStatus status) {
        this.id = id;
        this.date = date;
        this.cardSystem = cardSystem;
        this.pan = pan;

        this.is3D = is3D;
        this.cardHolder = cardHolder;
        this.amount = amount;
        this.status = status;
    }

    public static void main(String[] args) throws IOException {
        List<TransactionOperationRow> ops = new ArrayList<>();
        ops.add(TransactionOperationRow.builder()
                .actionCode("132")
                .amount(Money.parse("USD 100"))
                .approvalCode("qweqw")
                .id(UUID.randomUUID())
                .date(OffsetDateTime.now())
                .refNum("asdad")
                .isSuccess(true)
                .type(TransactionOperationType.REFUND)
                .transactionId(UUID.randomUUID())
                .createTransactionOperationRow());

        TransactionRow row = TransactionRow.builder()
                .id(UUID.randomUUID())
                .amount(Money.parse("USD 100"))
                .cardHolder("card holder")
                .cardSystem(CardSystemName.VISA)
                .date(OffsetDateTime.now())
                .is3D(true)
                .pan("4111****3455")
                .status(TransactionStatus.REFUNDED)

        .createTransactionRow();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("json"), row);
    }
}
