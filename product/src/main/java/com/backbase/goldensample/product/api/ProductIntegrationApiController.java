package com.backbase.goldensample.product.api;

import com.backbase.goldensample.product.service.ProductService;
import com.backbase.product.api.integration.v2.ProductIntegrationApi;
import com.backbase.product.api.service.v2.model.Product;
import com.backbase.product.api.service.v2.model.ProductId;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class <code>ProductController</code> is the implementation of the main Product Endpoint API definition.
 *
 * @see ProductIntegrationApi
 */
@RestController
@RequestMapping
@Slf4j
public class ProductIntegrationApiController implements ProductIntegrationApi {
    /**
     * Product service business logic interface.
     */
    private final ProductService prodService;

    @Autowired
    public ProductIntegrationApiController(ProductService prodService) {
        this.prodService = prodService;
    }


    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        prodService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<Product> getProductById(Long productId) {
        return ResponseEntity.ok(prodService.getProduct(productId, 0, 0));
    }

    @Override
    public ResponseEntity<ProductId> postProduct(@Valid Product product) {
        Product productWithId = prodService.createProduct(product);
        ProductId productId = new ProductId();
        productId.setId(productWithId.getProductId());

        return ResponseEntity.ok(productId);
    }

    @Override
    public ResponseEntity<Void> putProduct(@Valid Product product) {
        Product productWithId = prodService.updateProduct(product);
        log.debug("product with {} updated...", productWithId.getProductId());
        return ResponseEntity.noContent().build();
    }
}