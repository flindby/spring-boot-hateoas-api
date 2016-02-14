package flindby.boot.test.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import flindby.boot.test.domain.Product;
import flindby.boot.test.rest.ProductController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProductResource extends ResourceSupport {

    private final Product p;

    public ProductResource(final Product p) {
        this.p = p;
        add(linkTo(methodOn(ProductController.class).getProductById(p.getId())).withSelfRel());
    }

    @JsonProperty("id")
    public String id() {
        return p.getId();
    }

    @JsonProperty("brand")
    public String getBrand() {
        return p.getBrand();
    }

    @JsonProperty("title")
    public String getTitle() {
        return p.getTitle();
    }

    @JsonProperty("price")
    public Double getPrice() {
        return p.getPrice();
    }
}
