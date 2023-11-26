package apapedia.frontend_web.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import aj.org.objectweb.asm.TypeReference;

import apapedia.frontend_web.service.CartService;
import apapedia.frontend_web.dto.request.CreateCartItemRequestDTO;
import apapedia.frontend_web.dto.request.CreateCartRequestDTO;
import apapedia.frontend_web.dto.response.CartDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void addCart(CreateCartRequestDTO cartDTO){
        String url = "http://localhost:8083/api/cart/create";
        restTemplate.postForEntity(url, cartDTO, CreateCartRequestDTO.class);
    }

    @Override
    public void addCartItem(UUID cartId, CreateCartItemRequestDTO cartItemDTO){
        String url = "http://localhost:8083/api/cart/add-item/" + cartId.toString();
        restTemplate.postForEntity(url, cartItemDTO, CreateCartItemRequestDTO.class);
    }

    @Override
    public CartDTO getCartByUser(UUID userId){
        String url = "http://localhost:8083/api/cart/items/" + userId.toString();
        ResponseEntity<CartDTO> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<CartDTO>() {}
        );
        return response.getBody();
    }

}