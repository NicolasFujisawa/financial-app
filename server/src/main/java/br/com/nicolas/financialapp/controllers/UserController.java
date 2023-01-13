package br.com.nicolas.financialapp.controllers;

import br.com.nicolas.financialapp.controllers.dto.TransferResponse;
import br.com.nicolas.financialapp.controllers.dto.UserDTO;
import br.com.nicolas.financialapp.models.User;
import br.com.nicolas.financialapp.services.TransferService;
import br.com.nicolas.financialapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
        User user = userService.createUser(userDto);
        UserDTO responseDto = new UserDTO(user);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{userId}/bankStatement")
    public ResponseEntity<List<TransferResponse>> getBankStatement(@PathVariable("userId") Long userId) {
        List<TransferResponse> transferResponses = this.transferService.getAllTransfersByUser(userId)
                .stream()
                .map((TransferResponse::new))
                .peek((transferResponse) ->
                        transferResponse.setDirection(
                                Objects.equals(userId, transferResponse.getPayerId()) ? "SENT" : "INCOMING"))
                .collect(Collectors.toList());
        return new ResponseEntity<>(transferResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Long userId) throws ChangeSetPersister.NotFoundException {
        User user = this.userService.getUser(userId);
        UserDTO responseDto = new UserDTO(user);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersDTO = this.userService.getAllUsers()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }
}
