package apapedia.frontend_web.dto.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;

public class CreateCatalogRequestDTO {

    @NotNull
    private String fotoCatalog;

    @NotNull
    private String namaCatalog;

    @NotNull
    private Integer kategoriCatalog;

    @NotNull
    private String deskripsiCatalog;

    @NotNull
    private Integer stokCatalog;

    @NotNull
    @Positive(message = "Harga tidak boleh negatif")
    private Long harga;
}
