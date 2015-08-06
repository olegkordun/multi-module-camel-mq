package me.kordun.reports;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class OrderReport extends Report<OrderReportRow> {
    @JsonCreator
    public OrderReport(@JsonProperty("rows") List<OrderReportRow> rows,
                       @JsonProperty("totalRecords") Integer totalRecords,
                       @JsonProperty("totalDisplayRecords") Integer totalDisplayRecords) {
        super(rows, totalRecords, totalDisplayRecords);
    }
}
