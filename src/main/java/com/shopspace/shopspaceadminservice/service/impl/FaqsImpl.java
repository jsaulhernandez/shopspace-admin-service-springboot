package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.FaqsClient;
import com.shopspace.shopspaceadminservice.dto.FaqDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.FaqsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FaqsImpl implements FaqsService {
    Logger logger = LoggerFactory.getLogger(FaqsImpl.class);
    @Autowired
    private FaqsClient faqsClient;

    @Override
    public PageDTO<FaqDTO[]> getPagedFaqs(String search, Integer page, Integer size) {
        return faqsClient.getPagedFaqs(search, page, size);
    }

    @Override
    public FaqDTO create(FaqDTO faqDTO){
        return faqsClient.create(faqDTO);
    }

    @Override
    public FaqDTO update(FaqDTO faqDTO, Long id){
        Optional<FaqDTO> oldFaq = faqsClient.getOneFaq(id);

        if (oldFaq.isEmpty()) throw new DataNotFoundException("The questions to update doesn't exists.");

        faqDTO.setId(oldFaq.get().getId());

        return faqsClient.create(faqDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<FaqDTO> brand = faqsClient.getOneFaq(id);
        if (brand.isEmpty()) throw new DataNotFoundException("The questions to remove doesn't exists.");

        return faqsClient.delete(id);
    }
}
