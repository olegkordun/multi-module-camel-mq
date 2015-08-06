package me.kordun.reports;

import java.util.Collections;
import java.util.List;

public abstract class Report<T> {
    private final List<T> rows;
    private final Integer totalRecords;
    private final Integer totalDisplayRecords;

    public Report(List<T> rows, Integer totalRecords, Integer totalDisplayRecords) {
        this.rows = rows;
        this.totalRecords = totalRecords;
        this.totalDisplayRecords = totalDisplayRecords;
    }

    public List<T> getRows() {
        return Collections.unmodifiableList(rows);
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public Integer getTotalDisplayRecords() {
        return totalDisplayRecords;
    }
}
