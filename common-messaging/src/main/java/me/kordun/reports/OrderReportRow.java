package me.kordun.reports;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.kordun.enums.Currency;
import me.kordun.enums.OrderStatus;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.PropertyDefinition;
import org.joda.money.Money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.joda.beans.Bean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

public class OrderReportRow implements Serializable {
    private final UUID id;
    private final String merchantName;
    private final String orderId;
    private final BigDecimal amount;
    private final Currency currency;
    private final String startDate;
    private final OrderStatus state;

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private UUID id;
        private String merchantName;
        private String orderId;
        private BigDecimal amount;
        private Currency currency;
        private String startDate;
        private OrderStatus state;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder merchantName(String merchantName) {
            this.merchantName = merchantName;
            return this;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder startDate(OffsetDateTime startDate) {
            this.startDate = startDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
            return this;
        }

        public Builder state(OrderStatus state) {
            this.state = state;
            return this;
        }

        public OrderReportRow createOrderReportRow() {
            return new OrderReportRow(id, merchantName, orderId, amount, currency, startDate, state);
        }
    }

    @JsonCreator
    public OrderReportRow(@JsonProperty("id") UUID id,
                          @JsonProperty("merchantName") String merchantName,
                          @JsonProperty("orderId") String orderId,
                          @JsonProperty("amount") BigDecimal amount,
                          @JsonProperty("currency") Currency currency,
                          @JsonProperty("startDate") String startDate,
                          @JsonProperty("state") OrderStatus state) {
        this.id = id;
        this.merchantName = merchantName;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.startDate = startDate;
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getStartDate() {
        return startDate;
    }

    public OrderStatus getState() {
        return state;
    }
}
