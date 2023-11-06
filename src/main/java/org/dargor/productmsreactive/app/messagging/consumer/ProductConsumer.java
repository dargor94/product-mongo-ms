package org.dargor.productmsreactive.app.messagging.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.productmsreactive.app.dto.ProductRequestDtoWrapper;
import org.dargor.productmsreactive.app.service.ProductService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.dargor.productmsreactive.app.messagging.Constants.PRODUCTS_GROUP_ID;
import static org.dargor.productmsreactive.app.messagging.Constants.PRODUCTS_TOPIC;

@Slf4j
@Service
@AllArgsConstructor
public class ProductConsumer {

    private final ProductService productService;

    @KafkaListener(topics = PRODUCTS_TOPIC, groupId = PRODUCTS_GROUP_ID)
    public void consume(ProductRequestDtoWrapper request) {
        productService.saveProducts(request);
    }

}
