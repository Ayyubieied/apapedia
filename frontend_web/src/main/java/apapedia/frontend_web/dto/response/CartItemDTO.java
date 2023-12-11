package apapedia.frontend_web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import apapedia.frontend_web.dto.response.CartDTO;

import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(value={"cart"}, allowSetters = true)
public class CartItemDTO {
    private UUID itemId;
    private UUID productId;
    private Integer quantity;
    private CartDTO cart;
    private Boolean isDeleted;
}
