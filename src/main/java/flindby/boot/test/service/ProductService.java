package flindby.boot.test.service;


import flindby.boot.test.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    Optional<Product> getById(final String id);
}
