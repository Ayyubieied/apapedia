package apapedia.catalog.dto.mapper;

import apapedia.catalog.dto.request.CreateCatalogRequestDTO;
import apapedia.catalog.dto.request.UpdateCatalogRequestDTO;
import apapedia.catalog.model.Catalog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogMapper {
    Catalog createCatalogRequestDTOToCatalog(CreateCatalogRequestDTO createCatalogRequestDTO);

    Catalog updateCatalogRequestDTOToCatalog(UpdateCatalogRequestDTO updateCatalogRequestDTO);
}
