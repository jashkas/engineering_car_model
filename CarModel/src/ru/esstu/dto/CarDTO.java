package ru.esstu.dto;

public class CarDTO {
    private int id;
    private CarModelDTO carModel;
    private DealerCenterDTO dealerCenter;
    private CarDTOStatus status;
    private String configuration;
    private Color color;
    private double price;

    public CarDTO(int id, CarModelDTO carModel, DealerCenterDTO dealerCenter,
                  CarDTOStatus status, String configuration, Color color, double price) {
        this.id = id;
        this.carModel = carModel;
        this.dealerCenter = dealerCenter;
        this.status = status;
        this.configuration = configuration;
        this.color = color;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarModelDTO getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelDTO carModel) {
        this.carModel = carModel;
    }

    public DealerCenterDTO getDealerCenter() {
        return dealerCenter;
    }

    public void setDealerCenter(DealerCenterDTO dealerCenter) {
        this.dealerCenter = dealerCenter;
    }

    public CarDTOStatus getStatus() {
        return status;
    }

    public void setState(CarDTOStatus status) {
        this.status = status;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return id == carDTO.id;
    }
}

