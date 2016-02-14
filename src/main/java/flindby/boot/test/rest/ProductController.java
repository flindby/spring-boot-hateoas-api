package flindby.boot.test.rest;

import flindby.boot.test.resource.ClientInfo;
import flindby.boot.test.resource.ProductResource;
import flindby.boot.test.rest.exception.ProductNotFoundException;
import flindby.boot.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @ResponseStatus(OK)
    @RequestMapping(method = GET, produces = MediaTypes.HAL_JSON_VALUE)
    public Resources<ProductResource> getProducts() {
        return new Resources<>(
                productService.getAll()
                        .stream()
                        .map(ProductResource::new)
                        .collect(Collectors.toList())
        );
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseStatus(OK)
    public ProductResource getProductById(@PathVariable String id) {
        return new ProductResource(productService.getById(id)
        .orElseThrow(() -> new ProductNotFoundException(id)));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ClientInfo handleProductNotFound(Exception ex) {
        return new ClientInfo(ex.getMessage());
    }

}