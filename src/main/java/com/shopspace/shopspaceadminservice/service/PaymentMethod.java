package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.PaymentMethodDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

import java.util.List;

public interface PaymentMethod {
    PageDTO<PaymentMethodDTO[]> getPagedPaymentsMethods(String search, Integer page, Integer size);

    PaymentMethodDTO create(PaymentMethodDTO paymentMethodDTO);

    PaymentMethodDTO update(PaymentMethodDTO paymentMethodDTO, Long id);

    Boolean delete(Long id);
}
