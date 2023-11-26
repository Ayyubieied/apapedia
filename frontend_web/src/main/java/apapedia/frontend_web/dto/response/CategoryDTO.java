package apapedia.frontend_web.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class CategoryDTO {
    private UUID id;
    private String name;
}
