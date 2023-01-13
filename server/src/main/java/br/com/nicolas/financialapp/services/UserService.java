package br.com.nicolas.financialapp.services;

import br.com.nicolas.financialapp.controllers.dto.UserDTO;
import br.com.nicolas.financialapp.models.User;
import br.com.nicolas.financialapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return this.userRepository.save(user);
    }

    public User getUser(Long userId) throws ChangeSetPersister.NotFoundException {
        return this.userRepository.findById(userId).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
