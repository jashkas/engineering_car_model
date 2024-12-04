package test.java.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.esstu.lab1.carmodel.CarModelDTO;
import ru.esstu.lab1.service.CarModelService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class CarModelServiceTest {
    private CarModelService carModelService;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Создаем временный файл с тестовыми данными
        tempFile = Files.createTempFile("carModels", ".csv");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            writer.write("BMW;1 Series Convertible;Германия;DE\n");
            writer.write("Audi;A4;Германия;DE\n");
            writer.write("Toyota;Camry;Япония;JP\n");
            writer.write("BMW;X7;Германия;DE\n");
        }

        // Инициализируем CarModelService с путем к временным данным
        carModelService = new CarModelService(tempFile.toString());
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Удаляем временный файл после теста
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testGetAllCarModels() {
        // Получаем все модели автомобилей
        List<CarModelDTO> carModels = carModelService.getAllCarModels();

        // Проверяем, что данные загружены корректно
        assertEquals(4, carModels.size(), "Должно быть загружено 4 модели автомобилей");

        // Проверяем три модели
        assertEquals("BMW", carModels.get(0).getBrand(), "Должен быть BMW");
        assertEquals("1 Series Convertible", carModels.get(0).getModel(), "Модель должна быть 1 Series Convertible");
        assertEquals("Германия", carModels.get(0).getCountryOrigin(), "Страна должна быть Германия");
        assertEquals("DE", carModels.get(0).getCountryCode(), "Код должен быть DE");

        assertEquals("Audi", carModels.get(1).getBrand(), "Должен быть Audi");
        assertEquals("A4", carModels.get(1).getModel(), "Модель должна быть A4");
        assertEquals("Германия", carModels.get(1).getCountryOrigin(), "Страна должна быть Германия");
        assertEquals("DE", carModels.get(1).getCountryCode(), "Код должен быть DE");

        assertEquals("Toyota", carModels.get(2).getBrand(), "Должен быть Toyota");
        assertEquals("Camry", carModels.get(2).getModel(), "Модель должна быть Camry");
        assertEquals("Япония", carModels.get(2).getCountryOrigin(), "Страна должна быть Япония");
        assertEquals("JP", carModels.get(2).getCountryCode(), "Код должен быть JP");

        assertEquals("BMW", carModels.get(3).getBrand(), "Должен быть BMW");
        assertEquals("X7", carModels.get(3).getModel(), "Модель должна быть X7");
        assertEquals("Германия", carModels.get(3).getCountryOrigin(), "Страна должна быть Германия");
        assertEquals("DE", carModels.get(3).getCountryCode(), "Код должен быть DE");
    }

    @Test
    public void testGetAllCarModelsByBrand() {
        String brand = "Toyota";
        List<CarModelDTO> toyotaModels = carModelService.getAllCarModels(brand);
        assertEquals(1, toyotaModels.size(), "Должна быть 1 модель Toyota");
        assertEquals("Toyota", toyotaModels.get(0).getBrand(), "Неверный бренд модели");
    }

    @Test
    public void testFindCarById() {
        // Получаем модель автомобиля по id
        Optional<CarModelDTO> car = carModelService.findCarById(2);

        assertEquals("Audi", car.get().getBrand(), "Должен быть Audi");
        assertEquals("A4", car.get().getModel(), "Модель должна быть A4");
        assertEquals("Германия", car.get().getCountryOrigin(), "Страна должна быть Германия");
        assertEquals("DE", car.get().getCountryCode(), "Код должен быть DE");
    }

    @Test
    public void testFindCarByIdNotFound() {
        // Получаем модель автомобиля по несуществующему id
        Optional<CarModelDTO> car = carModelService.findCarById(10);
        assertFalse(car.isPresent(), "Модель автомобиля не должна существовать");
    }

    @Test
    public void testGetCarModelGroupByModel() {
        String brand = "BMW";
        Map<String, Integer> groupedModels = carModelService.getCarModelGroupByModel(brand);
        assertEquals(2, groupedModels.size(), "Моделей с таким брендом должно быть 2");

        assertEquals(1, groupedModels.get("1 Series Convertible").intValue(), "Количество моделей 1 Series Convertible должно быть 1");
        assertEquals(1, groupedModels.get("X7").intValue(), "Количество моделей X7 должно быть 1");
    }

    @Test
    public void testGetUniqueCarBrands() {
        List<String> uniqueBrands = carModelService.getUniqueCarBrands();

        // Проверка правильности количества уникальных брендов
        assertEquals(3, uniqueBrands.size(), "Should have 3 unique brands");

        // Проверка наличия указанных брендов
        assertTrue(uniqueBrands.contains("BMW"), "Should contain BMW");
        assertTrue(uniqueBrands.contains("Audi"), "Should contain Audi");
        assertTrue(uniqueBrands.contains("Toyota"), "Should contain Toyota");
    }
}

