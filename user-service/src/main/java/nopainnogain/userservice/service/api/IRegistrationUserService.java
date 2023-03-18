package nopainnogain.userservice.service.api;


import nopainnogain.userservice.core.dto.user.*;
import nopainnogain.userservice.entity.User;

public interface IRegistrationUserService {

    User registrationUser(RegistrationDto userRegistrationDTO);

    boolean verification(String code, String email);

//    void verification(VerificationDto verificationDto);
    DetailsDto login(LoginDto loginDto);

    SaveUserDto aboutMe();
}
