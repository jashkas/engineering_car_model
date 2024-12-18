package ru.esstu.entity;

public class CarModelEntity {
    private Long id;
    private String brand;
    private String model;

    public CarModelEntity() {}

    public CarModelEntity(Long id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }
    public Long getId() {
        return id;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
