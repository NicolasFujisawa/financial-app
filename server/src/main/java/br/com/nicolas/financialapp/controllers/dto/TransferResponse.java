package br.com.nicolas.financialapp.controllers.dto;

import br.com.nicolas.financialapp.models.PaymentStatus;
import br.com.nicolas.financialapp.models.Transfer;

import java.util.Date;

public class TransferResponse {

    private Long payerId;
    private Long payeeId;
    private Double amount;
    private Double tax;
    private Date scheduledDate;
    private Date transferDate;
    private PaymentStatus paymentStatus;
    private String direction;

    public TransferResponse() {
    }

    public TransferResponse(Transfer transfer) {
        this.payerId = transfer.getPayer().getId();
        this.payeeId = transfer.getPayee().getId();
        this.amount = transfer.getAmount();
        this.tax = transfer.getTax();
        this.scheduledDate = transfer.getScheduledDate();
        this.transferDate = transfer.getTransferDate();
        this.paymentStatus = transfer.getPaymentStatus();
    }

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

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public static TransferResponse parse(Transfer transfer) {
        TransferResponse response = new TransferResponse();
        response.setPayerId(transfer.getPayer().getId());
        response.setPayeeId(transfer.getPayee().getId());
        response.setAmount(transfer.getAmount());
        response.setTax(transfer.getTax());
        response.setScheduledDate(transfer.getScheduledDate());
        response.setTransferDate(transfer.getTransferDate());
        response.setPaymentStatus(transfer.getPaymentStatus());
        return response;
    }
}
