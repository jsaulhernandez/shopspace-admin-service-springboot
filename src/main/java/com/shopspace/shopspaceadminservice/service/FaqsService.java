package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.FaqDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface FaqsService {
    PageDTO<FaqDTO[]> getPagedFaqs(String search, Integer page, Integer size);

    FaqDTO create(FaqDTO faqDTO);

    FaqDTO update(FaqDTO faqDTO, Long id);

    Boolean delete(Long id);
}
