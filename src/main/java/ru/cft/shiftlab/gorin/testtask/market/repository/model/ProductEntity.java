package ru.cft.shiftlab.gorin.testtask.market.repository.model;

import com.sun.istack.NotNull;
import ru.cft.shiftlab.gorin.testtask.market.model.ProductType;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price_value")
    private long priceValue;

    @Column(name = "amount")
    private int amount;

    @Column(name = "product_type")
    private ProductType productType;


    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }


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


    public ProductType getProductType() {
        return productType;
    }
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }


    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
