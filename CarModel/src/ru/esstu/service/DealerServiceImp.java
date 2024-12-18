package ru.esstu.service;

import ru.esstu.dto.CarDTO;
import ru.esstu.dto.DealerCenterDTO;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DealerServiceImp {
    public DealerCenterDTO processCar(DealerCenterDTO dealer, List<CarDTO> cars) {
        if (dealer == null || cars == null) {
            return dealer;
        }
        // Проводить обработку списка
        List<CarDTO> showroomCars = dealer.getCarsInShowroom();
        List<CarDTO> carsToAddShowroom = new ArrayList<>();

        // добавлять в showroom нужно только те, которых ещё нет
        if (showroomCars != null) {
            synchronized (dealer) {
                for (CarDTO car : cars) {
                    // Если машины нет в showroom или если ее нужно добавить
                    if (!showroomCars.contains(car) || isNeedAddToShowroom(car)) {
                        carsToAddShowroom.add(car);
                    }
                }
                //добавлять в список showroom
                dealer.setCarsInShowroom(carsToAddShowroom);
            }
        }
        return dealer;
    }

    private boolean isNeedAddToShowroom(CarDTO car) {
        // проводить обработку списка
        if ( car.getColor().equals(Color.BLACK) &&
                ( car.getDealerCenter().getName().equals("BMW") &&
                        car.getCarModel().getModel().equals("X5") ) ) {
            return true;
        }
        return false;
    }

}