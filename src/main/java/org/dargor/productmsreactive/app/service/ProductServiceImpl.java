package org.dargor.productmsreactive.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.productmsreactive.app.dto.ProductRequestDtoWrapper;
import org.dargor.productmsreactive.core.repository.CustomerRepository;
import org.dargor.productmsreactive.core.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CustomerRepository customerRepository;

    @Override
    public void saveProducts(ProductRequestDtoWrapper productRequest) {
        try {
            var customerEntity = customerRepository.findById(productRequest.getCustomerId())
                    .map(customer -> {
                        var products = MapperUtil.toProducts(productRequest.getProducts());
                        Optional.ofNullable(customer.getProducts())
                                .ifPresentOrElse(products1 -> customer
                                        .getProducts().addAll(products), () -> customer
                                        .setProducts(products));
                        return customer;
                    });
            customerEntity.flatMap(customerRepository::save).subscribe(customer -> log.info(customer.getProducts().get(0).getDenomination()));
        } catch (Exception e) {
            log.error(String.format("Consumer error %s", e.getMessage()));
        }
    }
}