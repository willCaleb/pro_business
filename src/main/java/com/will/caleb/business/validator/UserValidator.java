package com.will.caleb.business.validator;

import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.EnumCustomException;
import com.will.caleb.business.model.entity.User;
import com.will.caleb.business.repository.UserRepository;

public class UserValidator extends AbstractValidator{

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(User user) {
        validateRequiredFields(user);
    }

    public void validateAvailableUsername(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new CustomException(EnumCustomException.USER_DUPLICATED_EMAIL);
        }
    }

    private void validateRequiredFields(User user) {
       addFieldToValidate("E-mail", user.getUsername());
       addFieldToValidate("Senha", user.getPassword());
       validate();
    }

}
