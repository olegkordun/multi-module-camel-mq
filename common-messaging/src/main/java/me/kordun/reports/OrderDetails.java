package me.kordun.reports;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.kordun.enums.OrderStatus;
import org.joda.money.Money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


public final class OrderDetails implements Serializable {
    @JsonProperty("id")
    private final UUID id;
    @JsonProperty("merchantId")
    private final UUID merchantId;
    @JsonIgnore
    private final Money amount;
    @JsonIgnore
    private final OffsetDateTime date;
    @JsonProperty("state")
    private final OrderStatus state;
    @JsonProperty("transactions")
    private final List<TransactionRow> transactions;


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

    public UUID getMerchantId() {
        return merchantId;
    }

    public OrderStatus getState() {
        return state;
    }

    public List<TransactionRow> getTransactions() {
        return transactions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private UUID merchantId;
        private Money amount;
        private OffsetDateTime date;
        private OrderStatus state;
        private List<TransactionRow> transactions;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder merchantId(UUID merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder amount(Money amount) {
            this.amount = amount;
            return this;
        }

        public Builder date(OffsetDateTime date) {
            this.date = date;
            return this;
        }

        public Builder state(OrderStatus state) {
            this.state = state;
            return this;
        }

        public Builder transactions(List<TransactionRow> transactions) {
            this.transactions = transactions;
            return this;
        }

        public OrderDetails createOrderDetails() {
            return new OrderDetails(id, merchantId, amount, date, state, transactions);
        }
    }
    public OrderDetails(UUID id, UUID merchantId, Money amount, OffsetDateTime date, OrderStatus state, List<TransactionRow> transactions) {
        this.id = id;
        this.merchantId = merchantId;
        this.amount = amount;
        this.date = date;
        this.state = state;
        this.transactions = transactions;
    }
}
