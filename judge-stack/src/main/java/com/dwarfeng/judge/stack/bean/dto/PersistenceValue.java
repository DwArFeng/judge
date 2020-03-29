package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Date;
import java.util.Objects;

/**
 * 持久数据值。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class PersistenceValue implements Dto {

    private static final long serialVersionUID = 4910869511230862532L;

    private String value;
    private Date happenedDate;

    public PersistenceValue() {
    }

    public PersistenceValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersistenceValue that = (PersistenceValue) o;

        if (!Objects.equals(value, that.value)) return false;
        return Objects.equals(happenedDate, that.happenedDate);
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (happenedDate != null ? happenedDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersistenceValue{" +
                "value='" + value + '\'' +
                ", happenedDate=" + happenedDate +
                '}';
    }
}
