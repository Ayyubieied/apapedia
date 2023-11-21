package main.java.apapedia.user.service;

import main.java.apapedia.user.model.Seller;
import java.util.List;
import java.util.UUID;

public interface SellerService {
    List<Seller> getAllSeller();
    Seller getSellerById(UUID id);
    Seller editSeller(Seller seller);
}
