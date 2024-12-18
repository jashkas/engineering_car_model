package ru.esstu.service;

import ru.esstu.carmodel.*;
import ru.esstu.lab1.carmodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DealerCenterService {
    public DealerCenterDTO createDealerCenter(String name, List<CarModelDTO> carModels) {
        List<CarDTO> cars = new ArrayList<>();
        Random random = new Random();
        String[] colors = {
                "синий", "красный", "зелёный", "жёлтый", "чёрный", "белый",
                "оранжевый", "фиолетовый", "коричневый", "розовый", "голубой", "серый",
                "бежевый", "лиловый", "бирюзовый", "персиковый", "салатовый", "бордовый",
                "пурпурный", "кремовый"
        };

        // создание 10 CarDTO
        for (int i = 0; i < 10; i++) {
            CarModelDTO carModel = carModels.get(random.nextInt(carModels.size()));
            CarDTOStatus status = CarDTOStatus.values()[random.nextInt(CarDTOStatus.values().length)];
            String configuration = CarDTOConfiguration.get();
            String color = colors[random.nextInt(colors.length)];
            double price = 200000 + random.nextInt(8000000);

            CarDTO car = new CarDTO(i, carModel, null, status, configuration, color, price);
            cars.add(car);
        }

        DealerCenterDTO dealerCenter = new DealerCenterDTO(name, cars);

        cars.forEach(car -> car.setDealerCenter(dealerCenter));

        return dealerCenter;
    }
}
