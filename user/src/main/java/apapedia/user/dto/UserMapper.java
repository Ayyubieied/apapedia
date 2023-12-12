package apapedia.user.DTO;

import org.mapstruct.Mapper;

import apapedia.user.DTO.response.UserResponseDTO;
import apapedia.user.DTO.auth.RegisterRequest;
import apapedia.user.DTO.auth.RegisterSellerRequest;
import apapedia.user.DTO.request.*;
import apapedia.user.DTO.response.CustomerResponse;
import apapedia.user.DTO.response.SellerResponse;
import apapedia.user.model.Customer;
import apapedia.user.model.Seller;
import apapedia.user.model.UserApapedia;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserApapedia createUserRequestDTOToUser(RegisterRequest createUserRequestDTO);
    UserResponseDTO userToUserResponseDTO(UserApapedia user);

    Seller createUserRequestDTOToSeller(RegisterSellerRequest createUserRequestDTO);
    SellerResponse sellerToSellerResponseDTO(Seller seller);

    Customer createUserRequestDTOToCustomer(RegisterRequest customerResponseDTO);
    CustomerResponse customerToCustomerResponseDTO(Customer customer);

    UserApapedia updateUserRequestDTOToUser(UpdateUserRequestDTO updateUserRequestDTO);

    Seller updateUserRequestDTOToSeller(UpdateUserRequestDTO updateUserRequestDTO);

    Customer updateUserRequestDTOToCustomer(UpdateUserRequestDTO updateUserRequestDTO);
}
