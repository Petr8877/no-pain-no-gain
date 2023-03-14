package nopainnogain.userservice.service.api;

import nopainnogain.userservice.core.dto.PageDto;
import nopainnogain.userservice.core.dto.user.SaveUserDto;
import nopainnogain.userservice.core.dto.user.UserDto;
import nopainnogain.userservice.entity.User;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUsersService {

    User createUser(UserDto userDTO);

    SaveUserDto getUser(UUID id);

    void updateUser(UUID id, LocalDateTime dtUpdate, UserDto userDTO);

    PageDto<SaveUserDto> getUsersPage(Pageable pageable);
}
