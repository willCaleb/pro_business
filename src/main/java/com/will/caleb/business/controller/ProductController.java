package com.will.caleb.business.controller;

import com.will.caleb.business.model.entity.Product;
import com.will.caleb.business.model.records.requests.ProductRequest;
import com.will.caleb.business.model.records.responses.artificial_intelligence.ProductAnaliseIAResponse;
import com.will.caleb.business.model.records.responses.ProductResponse;
import com.will.caleb.business.pattern.PageableBean;
import com.will.caleb.business.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ProductController.PATH)
@RequiredArgsConstructor
public class ProductController extends AbstractController{
    public static final String PATH = "${api.prefix.v1}/product";

    private final ProductService productService;

    @PostMapping("/include")
    ProductResponse include(@RequestBody ProductRequest request) {
        Product product = toEntity(request, Product.class);

        return toResponse(productService.incluir(product), ProductResponse.class);
    }

    @PutMapping("/edit")
    ProductResponse edit(@RequestBody ProductRequest request) {
        Product product = toEntity(request, Product.class);

        return toResponse(productService.edit(product), ProductResponse.class);
    }

    @GetMapping("/list")
    Page<ProductResponse> list(Pageable pageable) {
        Page<Product> productPage = productService.list(pageable);
        List<ProductResponse> responses = new ArrayList<>();

        productPage.forEach(product -> {
            responses.add(toResponse(product, ProductResponse.class));
        });

        return new PageableBean<ProductResponse>()
                .withPageable(pageable)
                .withContent(responses)
                .withTotal(productPage.getTotalElements())
                .getPaged();

    }

    @GetMapping("/ai-insights")
    public ProductAnaliseIAResponse generateAiAnalytics(Pageable pageable) {
        return productService.generateAiAnalytics(pageable);
    }
}
