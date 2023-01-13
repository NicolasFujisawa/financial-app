package br.com.nicolas.financialapp.controllers;

import br.com.nicolas.financialapp.controllers.dto.TransferRequest;
import br.com.nicolas.financialapp.controllers.dto.TransferResponse;
import br.com.nicolas.financialapp.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferResponse> createTransfer(@RequestBody TransferRequest transferRequest)
            throws ChangeSetPersister.NotFoundException {
        TransferResponse response =
                TransferResponse.parse(this.transferService.saveTransferWithoutTaxes(transferRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
