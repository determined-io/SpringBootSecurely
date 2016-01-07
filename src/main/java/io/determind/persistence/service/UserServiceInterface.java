package io.determind.persistence.service;

import io.determind.persistence.model.User;
import java.util.Optional;

public interface UserServiceInterface {

    Optional<User> getUserByEmail(String email);

}
