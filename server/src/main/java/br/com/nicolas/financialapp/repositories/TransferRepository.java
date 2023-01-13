package br.com.nicolas.financialapp.repositories;

import br.com.nicolas.financialapp.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByPayeeIdOrPayerIdOrderByTransferDateDesc(Long userId, Long userId1);
}
