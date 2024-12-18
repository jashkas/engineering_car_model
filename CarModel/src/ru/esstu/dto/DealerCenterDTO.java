package ru.esstu.dto;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DealerCenterDTO {
    private Long id;
    private Integer countCars;
    private Integer countShowroomCars;
    private String name;
    private List<CarDTO> carList;
    private List<CarDTO> carsInShowroom;

    public DealerCenterDTO(Long id, String name, List<CarDTO> carList, @Nullable List<CarDTO> carsInShowroom) {
        this.id = id;
        this.name = name;
        this.carList = carList;
        this.carsInShowroom = carsInShowroom;
        countCars = carList.size();
        if (carsInShowroom != null) {
            countShowroomCars = carsInShowroom.size();
        }
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountCars() {
        return countCars;
    }
    public Integer getCountShowroomCars() {
        return countShowroomCars;
    }

    public List<CarDTO> getCarList() {
        if (carList == null) {
            return new ArrayList<>();
        }
        return carList;
    }
    public void setCarList(List<CarDTO> carList) {
        this.carList = carList;
        this.countCars = carList.size();
    }

    public List<CarDTO> getCarsInShowroom() {
        if (carsInShowroom == null) {
            return new ArrayList<>();
        }
        return carsInShowroom;
    }
    public void setCarsInShowroom(List<CarDTO> carsInShowroom) {
        this.carsInShowroom = carsInShowroom;
        this.countShowroomCars = carsInShowroom.size();
    }
}
