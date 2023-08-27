package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.PaymentMethodClient;
import com.shopspace.shopspaceadminservice.dto.PaymentMethodDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodImpl implements PaymentMethod {
    Logger logger = LoggerFactory.getLogger(PaymentMethodImpl.class);
    @Autowired
    PaymentMethodClient paymentMethodClient;

    @Override
    public PageDTO<PaymentMethodDTO[]> getPagedPaymentsMethods(String search, Integer page, Integer size) {
        return paymentMethodClient.getPagedPaymentsMethods(search, page, size);
    }

    @Override
    public PaymentMethodDTO create(PaymentMethodDTO paymentMethodDTO){
        return paymentMethodClient.create(paymentMethodDTO);
    }

    @Override
    public PaymentMethodDTO update(PaymentMethodDTO paymentMethodDTO, Long id){
        Optional<PaymentMethodDTO> oldPaymentMethod = paymentMethodClient.getOnePaymentMethod(id);

        if (oldPaymentMethod.isEmpty()) throw new DataNotFoundException("El metodo de pago a modificar no existe.");

        paymentMethodDTO.setId(oldPaymentMethod.get().getId());

        return paymentMethodClient.create(paymentMethodDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<PaymentMethodDTO> oldPaymentMethod = paymentMethodClient.getOnePaymentMethod(id);
        if (oldPaymentMethod.isEmpty()) throw new DataNotFoundException("El metodo de pago a eliminar no existe.");

        return paymentMethodClient.delete(id);
    }
}
