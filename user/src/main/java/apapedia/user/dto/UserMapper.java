package apapedia.user.dto;

import org.mapstruct.Mapper;

<<<<<<< HEAD
import apapedia.user.dto.request.CreateUserRequestDTO;
import apapedia.user.dto.response.CustomerResponseDTO;
import apapedia.user.dto.response.SellerResponseDTO;
import apapedia.user.dto.response.UserResponseDTO;
=======
import apapedia.user.dto.auth.RegisterRequest;
import apapedia.user.dto.auth.RegisterSellerRequest;
import apapedia.user.dto.response.UserResponseDTO;
import apapedia.user.dto.request.*;
import apapedia.user.dto.response.CustomerResponse;
import apapedia.user.dto.response.SellerResponse;
>>>>>>> origin/development
import apapedia.user.model.Customer;
import apapedia.user.model.Seller;
import apapedia.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
<<<<<<< HEAD
    User createUserRequestDTOToUser(CreateUserRequestDTO createUserRequestDTO);
    UserResponseDTO userToUserResponseDTO(User user);

    Seller createUserRequestDTOToSeller(CreateUserRequestDTO createUserRequestDTO);
    SellerResponseDTO sellerToSellerResponseDTO(Seller seller);

    Customer createUserRequestDTOToCustomer(CreateUserRequestDTO customerResponseDTO);
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
=======
    User createUserRequestDTOToUser(RegisterRequest createUserRequestDTO);
    UserResponseDTO userToUserResponseDTO(User user);

    Seller createUserRequestDTOToSeller(RegisterSellerRequest createUserRequestDTO);
    SellerResponse sellerToSellerResponseDTO(Seller seller);

    Customer createUserRequestDTOToCustomer(RegisterRequest customerResponseDTO);
    CustomerResponse customerToCustomerResponseDTO(Customer customer);

    User updateUserRequestDTOToUser(UpdateUserRequestDTO updateUserRequestDTO);

    Seller updateUserRequestDTOToSeller(UpdateUserRequestDTO updateUserRequestDTO);

    Customer updateUserRequestDTOToCustomer(UpdateUserRequestDTO updateUserRequestDTO);
>>>>>>> origin/development
}
