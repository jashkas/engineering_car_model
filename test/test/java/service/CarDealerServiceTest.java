package test.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.esstu.carmodel.CarDTO;
import ru.esstu.carmodel.CarModelDTO;
import ru.esstu.carmodel.DealerCenterDTO;
import ru.esstu.service.DealerCenterService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarDealerServiceTest {
    private DealerCenterService carDealerService;
    private List<CarModelDTO> carModels;

    @BeforeEach
    void setUp() {
        carDealerService = new DealerCenterService();

        // Create mock data string array
        String[] modelData = {
                "BMW;1 Series Convertible;Германия;DE",
                "Audi;A4;Германия;DE",
                "Toyota;Camry;Япония;JP",
                "BMW;X7;Германия;DE"
        };

        carModels = new ArrayList<>();

        // Process each line to create CarModelDTO objects
        int id = 1;
        for (String data : modelData) {
            String[] parts = data.split(";");
            if (parts.length == 4) {
                String brand = parts[0];
                String model = parts[1];
                String country = parts[2];
                String code = parts[3];
                carModels.add(new CarModelDTO(id++, brand, model, country, code));
            }
        }
    }

    @Test
    void testCreateDealerCenter() {
        String dealerName = "Тестовый Дилерский Центр";
        DealerCenterDTO dealerCenter = carDealerService.createDealerCenter(dealerName, carModels);

        // Проверяем основные параметры дилерского центра
        assertNotNull(dealerCenter, "Дилерский центр не должен быть null");
        assertEquals(dealerName, dealerCenter.getName(), "Имя дилерского центра должно совпадать с указанным");

        // Проверяем, что создано 10 машин
        List<CarDTO> cars = dealerCenter.getCarList();
        assertNotNull(cars, "Список машин не должен быть null");
        assertEquals(10, cars.size(), "Должно быть 10 объектов CarDTO в списке");

        // Проверяем свойства каждой CarDTO
        for (CarDTO car : cars) {
            assertNotNull(car.getCarModel(), "Модель машины не должна быть null");
            assertTrue(carModels.contains(car.getCarModel()), "Модель машины должна быть одной из заданных моделей");
            assertNotNull(car.getStatus(), "Статус машины не должен быть null");
            assertNotNull(car.getConfiguration(), "Конфигурация машины не должна быть null");
            assertNotNull(car.getColor(), "Цвет машины не должен быть null");
            assertTrue(car.getPrice() >= 200000 && car.getPrice() <= 8200000, "Цена машины должна быть в ожидаемом диапазоне");
            assertEquals(dealerCenter, car.getDealerCenter(), "Дилерский центр должен быть связан с машиной");
        }
    }
}
