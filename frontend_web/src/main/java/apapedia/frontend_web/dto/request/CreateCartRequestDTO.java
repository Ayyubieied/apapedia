package apapedia.frontend_web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import apapedia.frontend_web.dto.response.CartItemDTO;

import java.util.UUID;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCartRequestDTO {
    private Integer totalPrice;
    private UUID userId;
    private List<CartItemDTO> listCartItem;
}