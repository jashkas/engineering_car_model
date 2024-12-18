package ru.esstu.carmodel;

import java.util.List;

public class DealerCenterDTO {
    private String name;
    private List<CarDTO> carList;

    public DealerCenterDTO(String name, List<CarDTO> carList) {
        this.name = name;
        this.carList = carList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarDTO> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDTO> carList) {
        this.carList = carList;
    }
}
