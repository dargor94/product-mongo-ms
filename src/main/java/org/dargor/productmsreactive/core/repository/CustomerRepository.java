package org.dargor.productmsreactive.core.repository;

import org.dargor.productmsreactive.core.entity.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

}
