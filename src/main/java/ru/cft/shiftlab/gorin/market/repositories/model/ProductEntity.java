package ru.cft.shiftlab.gorin.market.repositories.model;

import com.sun.istack.NotNull;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import ru.cft.shiftlab.gorin.market.model.enums.ProductType;

import javax.persistence.*;

/**
 * Сущность товара. От нее наследуются сущность Пк, сущность монитора, сущность ноутбука и сущность жесткого диска.
 * Наследование происходит с типом "Joined", т.е. таблицы сущностей и таблица товара связаны напрямую через id.
 * Поля хранятся в таблице "products"
 * @see HddEntity
 * @see LaptopEntity
 * @see MonitorEntity
 * @see PcEntity
 */
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private long id;

    @NotBlank
    @Column(name = "serial_number")
    private String serialNumber;

    @NotBlank
    @Column(name = "manufacturer")
    private String manufacturer;

    @NotNull
    @Column(name = "price_value")
    private long priceValue;

    @NotNull
    @Column(name = "amount")
    private int amount;

    @NotNull
    @Column(name = "product_type")
    private ProductType productType;


    public ProductEntity(String serialNumber, String manufacturer, long priceValue, int amount, ProductType productType) {
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.priceValue = priceValue;
        this.amount = amount;
        this.productType = productType;
    }

    public ProductEntity() {
    }

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


    @Override
    public String toString() {
        return "ProductEntity {" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", priceValue=" + priceValue +
                ", amount=" + amount +
                ", productType=" + productType +
                '}';
    }
}
