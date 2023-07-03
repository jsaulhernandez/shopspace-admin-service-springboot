package com.shopspace.shopspaceadminservice.dto.pageable;

public class PageableDTO {
    private SortDTO sort;
    private Integer offset;
    private Integer pageSize;
    private Integer pageNumber;
    private boolean paged;
    private boolean unpaged;

    public PageableDTO() {
    }

    public PageableDTO(SortDTO sort, Integer offset, Integer pageSize, Integer pageNumber, boolean paged, boolean unpaged) {
        this.sort = sort;
        this.offset = offset;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.paged = paged;
        this.unpaged = unpaged;
    }

    public SortDTO getSort() {
        return sort;
    }

    public void setSort(SortDTO sort) {
        this.sort = sort;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isPaged() {
        return paged;
    }

    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    public boolean isUnpaged() {
        return unpaged;
    }

    public void setUnpaged(boolean unpaged) {
        this.unpaged = unpaged;
    }

    @Override
    public String toString() {
        return "PageableDTO{" +
                "sort=" + sort +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", paged=" + paged +
                ", unpaged=" + unpaged +
                '}';
    }
}
