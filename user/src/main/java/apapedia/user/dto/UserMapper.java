package apapedia.user.dto;

import org.mapstruct.Mapper;

import apapedia.user.dto.request.*;
import apapedia.user.dto.response.CustomerResponseDTO;
import apapedia.user.dto.response.SellerResponseDTO;
import apapedia.user.dto.response.UserResponseDTO;
import apapedia.user.model.Customer;
import apapedia.user.model.Seller;
import apapedia.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUserRequestDTOToUser(CreateUserRequestDTO createUserRequestDTO);
    UserResponseDTO userToUserResponseDTO(User user);

    Seller createUserRequestDTOToSeller(CreateUserRequestDTO createUserRequestDTO);
    SellerResponseDTO sellerToSellerResponseDTO(Seller seller);

    Customer createUserRequestDTOToCustomer(CreateUserRequestDTO customerResponseDTO);
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);

    User updateUserRequestDTOToUser(UpdateUserRequestDTO updateUserRequestDTO);

    Seller updateUserRequestDTOToSeller(UpdateUserRequestDTO updateUserRequestDTO);

    Customer updateUserRequestDTOToCustomer(UpdateUserRequestDTO updateUserRequestDTO);
}
