package com.shopspace.shopspaceadminservice.dto.pageable;

public class SliceDTO {
    private Integer totalPages;
    private Integer totalElements;
    private boolean last;
    private Integer size;
    private Integer number;
    private SortDTO sort;
    private Integer numberOfElements;
    private boolean first;
    private boolean empty;

    public SliceDTO() {
    }

    public SliceDTO(Integer totalPages, Integer totalElements, boolean last, Integer size, Integer number, SortDTO sort, Integer numberOfElements, boolean first, boolean empty) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.last = last;
        this.size = size;
        this.number = number;
        this.sort = sort;
        this.numberOfElements = numberOfElements;
        this.first = first;
        this.empty = empty;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public SortDTO getSort() {
        return sort;
    }

    public void setSort(SortDTO sort) {
        this.sort = sort;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    @Override
    public String toString() {
        return "SliceDTO{" +
                "totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", last=" + last +
                ", size=" + size +
                ", number=" + number +
                ", sort=" + sort +
                ", numberOfElements=" + numberOfElements +
                ", first=" + first +
                ", empty=" + empty +
                '}';
    }
}
