package com.twohire.invoice.repository;

import com.twohire.invoice.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
