package br.com.nicolas.financialapp.controllers.dto;

import java.util.Date;
import java.util.Objects;

public class TransferRequest {

    private Long payerId;
    private Long payeeId;
    private Double amount;
    private Date scheduledDate;
    private Date transferDate;

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferRequest that = (TransferRequest) o;
        return Objects.equals(payerId, that.payerId) &&
                Objects.equals(payeeId, that.payeeId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(scheduledDate, that.scheduledDate) &&
                Objects.equals(transferDate, that.transferDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payerId, payeeId, amount, scheduledDate, transferDate);
    }
}
