package ru.cft.shiftlab.gorin.testtask.market.model;

import ru.cft.shiftlab.gorin.testtask.market.model.enums.ProductType;

public class ProductFeaturesDTO {
    private String serialNumber;

    private String manufacturer;

    private long priceValue;

    private int amount;

    private ProductType productType;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public long getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(long priceValue) {
        this.priceValue = priceValue;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
