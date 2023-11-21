package main.java.apapedia.user.service;

import main.java.apapedia.user.repository.SellerDb;
import main.java.apapedia.user.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerDb sellerDb;

    @Override
    public List<Seller> getAllSeller() {
        return sellerDb.findAll(); 
    }

    @Override
    public Seller getSellerById(UUID id) {
        for (Seller seller : getAllSeller()) {
            if (seller.getIdSeller().equals(id)) {
                return seller;
            }
        }
        return null;
    }

    @Override
    public Seller editSeller(Seller sellerDTO) {
        Seller seller = getSellerById(sellerDTO.getIdSeller());
        if (seller != null){
            seller.setName(sellerDTO.getName());
            seller.setUsername(sellerDTO.getUsername());
            seller.setPassword(sellerDTO.getPassword());
            seller.setEmail(sellerDTO.getEmail());
            seller.setAddress(sellerDTO.getAddress());
            sellerDb.save(seller);
        }
        return seller;
    }

}