package ru.esstu.entity;

public class CarEntity {
    private Long id;
    private CarModelEntity carModel;
    private DealerEntity dealer;
    private String countryOrigin;
    private String countryCode;
    private String status;
    private String configuration;
    private String color;
    private Double price;

    public CarEntity() {}
    public CarModelEntity getCarModel() {
        return carModel;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCarModel(CarModelEntity carModel) {
        this.carModel = carModel;
    }
    public DealerEntity getDealer() {
        return dealer;
    }
    public void setDealer(DealerEntity dealer) {
        this.dealer = dealer;
    }
    public String getCountryOrigin() {
        return countryOrigin;
    }
    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getConfiguration() {
        return configuration;
    }
    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
