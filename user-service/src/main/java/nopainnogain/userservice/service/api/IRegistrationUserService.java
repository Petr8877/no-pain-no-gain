package nopainnogain.userservice.service.api;


import nopainnogain.userservice.core.dto.user.DetailsDto;
import nopainnogain.userservice.core.dto.user.LoginDto;
import nopainnogain.userservice.core.dto.user.SaveUserDto;
import nopainnogain.userservice.core.dto.user.UserRegistrationDto;

public interface IRegistrationUserService {

    void registrationUser(UserRegistrationDto userRegistrationDTO);

    void verification(String code, String mail);

    DetailsDto login(LoginDto loginDto);

    SaveUserDto aboutMe();
}
