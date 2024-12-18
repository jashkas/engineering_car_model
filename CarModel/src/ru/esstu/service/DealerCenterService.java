package ru.esstu.service;

import ru.esstu.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DealerCenterService {
    public DealerCenterDTO createDealerCenter(String name, List<CarModelDTO> carModels, int count) {
        List<CarDTO> cars = new ArrayList<>();
        Random random = new Random();

        // создание 10 CarDTO
        for (int i = 0; i < count; i++) {
            CarModelDTO carModel = carModels.get(random.nextInt(carModels.size()));
            CarDTOStatus status = CarDTOStatus.values()[random.nextInt(CarDTOStatus.values().length)];
            String configuration = CarDTOConfiguration.get();
            Color color = Color.get(random.nextInt(Color.length));
            double price = 200000 + random.nextInt(8000000);

            CarDTO car = new CarDTO(i, carModel, null, status, configuration, color, price);
            cars.add(car);
        }

        DealerCenterDTO dealerCenter = new DealerCenterDTO(random.nextLong(), name, cars, null);

        cars.forEach(car -> car.setDealerCenter(dealerCenter));

        return dealerCenter;
    }
}
