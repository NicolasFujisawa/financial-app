package br.com.nicolas.financialapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 256)
    private String name;

    @Column(name = "email", length = 256)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payer")
    private List<Transfer> sentTransfers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payee")
    private List<Transfer> incomingTransfers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transfer> getSentTransfers() {
        return sentTransfers;
    }

    public void setSentTransfers(List<Transfer> sentTransfers) {
        this.sentTransfers = sentTransfers;
    }

    public List<Transfer> getIncomingTransfers() {
        return incomingTransfers;
    }

    public void setIncomingTransfers(List<Transfer> incomingTransfers) {
        this.incomingTransfers = incomingTransfers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) &&
                Objects.equals(this.name, user.name) &&
                Objects.equals(this.sentTransfers, user.sentTransfers) &&
                Objects.equals(this.incomingTransfers, user.incomingTransfers) &&
                Objects.equals(this.email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.sentTransfers, this.incomingTransfers, this.email);
    }
}
