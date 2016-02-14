package flindby.boot.test.service;

import flindby.boot.test.domain.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    // Init some static data
    private final static List<Product> products;
    static {
        products = new ArrayList<>();
        Arrays.asList("1,2,3,4,5,6,7,8,9".split(","))
                .forEach(i -> {
                    Product p = new Product();
                    p.setBrand("Apple " + i);
                    p.setTitle(UUID.randomUUID().toString().split("-")[0]);
                    p.setPrice(Double.valueOf(i));
                    products.add(p);
                });
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Optional<Product> getById(final String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
