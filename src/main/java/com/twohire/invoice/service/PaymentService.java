package com.twohire.invoice.service;

import com.twohire.invoice.model.Invoice;
import com.twohire.invoice.model.Payment;
import com.twohire.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public PaymentService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void defineInvoiceByPayment(final Payment payment) {
        Invoice invoice = new Invoice();
        invoice.setAmount(payment.getTotal() + payment.getFee());
        invoiceRepository.save(invoice);
    }
}
