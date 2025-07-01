package com.will.caleb.business.service.impl;

import com.will.caleb.business.model.entity.Product;
import com.will.caleb.business.repository.ProductRepository;
import com.will.caleb.business.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends AbstractServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product incluir(Product product) {

        product.setEnterprise(getIdEnterpriseByContext());
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product edit(Product product) {
        return null;
    }

    @Override
    public Page<Product> list(Pageable pageable) {
        return productRepository.findAllByEnterprise(getIdEnterpriseByContext(), pageable);
    }
}
