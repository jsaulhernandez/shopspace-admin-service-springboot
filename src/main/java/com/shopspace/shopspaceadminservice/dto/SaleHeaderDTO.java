package com.shopspace.shopspaceadminservice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SaleHeaderDTO {
    private Long id;
    private BigDecimal shippingCost;
    private BigDecimal tax;
    private Date saleDate;
    private PaymentMethodDTO paymentMethod;
    private CreditCardInformationDTO creditCardInformation;
    private CouponDTO coupon;
    private CustomerDTO customer;
    private List<SaleBodyDTO> detail;

    public SaleHeaderDTO() {
    }

    public SaleHeaderDTO(Long id, BigDecimal shippingCost, BigDecimal tax, Date saleDate, PaymentMethodDTO paymentMethod, CreditCardInformationDTO creditCardInformation, CouponDTO coupon, CustomerDTO customer, List<SaleBodyDTO> detail) {
        this.id = id;
        this.shippingCost = shippingCost;
        this.tax = tax;
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
        this.creditCardInformation = creditCardInformation;
        this.coupon = coupon;
        this.customer = customer;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CreditCardInformationDTO getCreditCardInformation() {
        return creditCardInformation;
    }

    public void setCreditCardInformation(CreditCardInformationDTO creditCardInformation) {
        this.creditCardInformation = creditCardInformation;
    }

    public CouponDTO getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponDTO coupon) {
        this.coupon = coupon;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<SaleBodyDTO> getDetail() {
        return detail;
    }

    public void setDetail(List<SaleBodyDTO> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "SaleHeaderDTO{" +
                "id=" + id +
                ", shippingCost=" + shippingCost +
                ", tax=" + tax +
                ", saleDate=" + saleDate +
                ", paymentMethod=" + paymentMethod +
                ", creditCardInformation=" + creditCardInformation +
                ", coupon=" + coupon +
                ", customer=" + customer +
                ", detail=" + detail +
                '}';
    }
}
