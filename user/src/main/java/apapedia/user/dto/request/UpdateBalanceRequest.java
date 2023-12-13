package apapedia.user.dto.request;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBalanceRequest {
    private UUID idUser;
    // @JsonProperty("withdrawal")
    private long money;
    private boolean withdrawn;
}
