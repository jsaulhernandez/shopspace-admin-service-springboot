package com.shopspace.shopspaceadminservice.dto.pageable;

public class PageDTO<T> extends SliceDTO{
    private T content;
    private PageableDTO pageable;

    public PageDTO() {
    }

    public PageDTO(T content, PageableDTO pageable) {
        this.content = content;
        this.pageable = pageable;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public PageableDTO getPageable() {
        return pageable;
    }

    public void setPageable(PageableDTO pageable) {
        this.pageable = pageable;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "content=" + content +
                ", pageable=" + pageable +
                '}';
    }
}
