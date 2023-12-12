package apapedia.user.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import apapedia.user.DTO.request.CreateUserRequestDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UpdateUserRequestDTO extends CreateUserRequestDTO {
    private UUID idUser;
}
