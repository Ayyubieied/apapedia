package apapedia.order.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import apapedia.order.DTO.request.CreateCartRequestDTO;
import apapedia.order.model.Cart;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UpdateCartRequestDTO extends CreateCartRequestDTO {
    private UUID cartId;
}