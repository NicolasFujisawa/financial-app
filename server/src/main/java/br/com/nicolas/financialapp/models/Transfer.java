package br.com.nicolas.financialapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "tranfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id", nullable = false)
    private User payee;

    @Column(name = "amount", precision = 15, scale = 2)
    private Double amount;

    @Column(name = "tax", precision = 15, scale = 2)
    private Double tax;

    @Column(name = "scheduled_date")
    @Temporal(TemporalType.DATE)
    private Date scheduledDate;

    @Column(name = "transfer_date")
    @Temporal(TemporalType.DATE)
    private Date transferDate;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public void calculateTax() {
        long diffInMillies = Math.abs(transferDate.getTime() - scheduledDate.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        double taxValue = 0;
        if (diffInDays == 0) {
            taxValue = amount * 0.03 + 3;
        } else if (diffInDays > 0 && diffInDays <= 10) {
            taxValue = 12;
        } else if (diffInDays > 10) {
            if (amount <= 1000) {
                taxValue = amount * 0.03 + 3;
            } else if (amount > 1000 && amount <= 2000) {
                taxValue = 12;
            } else {
                if (diffInDays <= 20) {
                    taxValue = amount * 0.082;
                } else if (diffInDays <= 30) {
                    taxValue = amount * 0.069;
                } else if (diffInDays <= 40) {
                    taxValue = amount * 0.047;
                } else {
                    taxValue = amount * 0.017;
                }
            }
        }
        this.amount -= taxValue;
        this.tax = taxValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return Objects.equals(id, transfer.id) &&
                Objects.equals(payer, transfer.payer) &&
                Objects.equals(payee, transfer.payee) &&
                Objects.equals(amount, transfer.amount) &&
                Objects.equals(tax, transfer.tax) &&
                Objects.equals(scheduledDate, transfer.scheduledDate) &&
                Objects.equals(transferDate, transfer.transferDate) &&
                paymentStatus == transfer.paymentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payer, payee, amount, tax, scheduledDate, transferDate, paymentStatus);
    }
}
