package br.com.nicolas.financialapp.services;

import br.com.nicolas.financialapp.controllers.dto.TransferRequest;
import br.com.nicolas.financialapp.models.PaymentStatus;
import br.com.nicolas.financialapp.models.Transfer;
import br.com.nicolas.financialapp.models.User;
import br.com.nicolas.financialapp.repositories.TransferRepository;
import br.com.nicolas.financialapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    private static final Logger LOG = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private UserRepository userRepository;

    public Transfer saveTransferWithoutTaxes(TransferRequest transferRequest) throws ChangeSetPersister.NotFoundException {
        Transfer transfer = this.convert(transferRequest);
        transfer.calculateTax();
        if (transfer.getTax() == null || transfer.getTax() == 0D) {
            LOG.warn("No tax were applied!");
        }
        transfer.setPaymentStatus(PaymentStatus.PENDING);
        return this.transferRepository.save(transfer);
    }

    public List<Transfer> getAllTransfersByUser(Long userId) {
        return this.transferRepository.findByPayeeIdOrPayerIdOrderByTransferDateDesc(userId, userId);
    }

    private Transfer convert(TransferRequest request) throws ChangeSetPersister.NotFoundException {
        Transfer transfer = new Transfer();
        User payer = this.userRepository.findById(request.getPayerId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        transfer.setPayer(payer);
        User payee = this.userRepository.findById(request.getPayeeId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        transfer.setPayee(payee);
        transfer.setAmount(request.getAmount());
        transfer.setScheduledDate(request.getScheduledDate());
        transfer.setTransferDate(request.getTransferDate());
        return transfer;
    }

}
