package apapedia.frontend_web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import apapedia.frontend_web.dto.response.CartItemDTO;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartDTO {
    private UUID cartId;
    private Integer totalPrice;
    private UUID userId;
    @JsonProperty("listCartItem")
    private List<CartItemDTO> listCartItem;
}
