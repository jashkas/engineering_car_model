package ru.esstu.lab1.service;
import ru.esstu.lab1.carmodel.CarModelDTO;
import ru.esstu.lab1.util.FileManagerCarModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarModelService implements FileManagerCarModelService{
    List<CarModelDTO> carModels;

    public CarModelService() {
        carModels = new ArrayList<>(); // создаем список
        load("default"); // загружаем данные из файла
    }

    public CarModelService(String filePath) {
        carModels = new ArrayList<>(); // создаем список
        load(filePath); // загружаем данные из файла
    }

    @Override
    public void load(String filePath) {
        // используем утилитный метод для загрузки моделей автомобилей
        CarModelReader.loadFromFile(filePath, carModels);
    }

    @Override
    public List<CarModelDTO> getAllCarModels() {
        return carModels;
    }

    @Override
    public List<CarModelDTO> getAllCarModels(String brand) {
        return carModels.stream()
                .filter(carModel -> carModel.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());  // группировка
    }

    @Override
    public Optional<CarModelDTO> findCarById(int id) {
        CarModelDTO searchCarModel = new CarModelDTO(id, null, null, null, null);
        if (carModels.contains(searchCarModel)) {
            return carModels.stream()
                    .filter(carModel -> carModel.getId() == id)
                    .findFirst(); // возвращает первый результат
        }
        return Optional.empty(); // если не нашли
    }

    @Override
    public Map<String, Integer> getCarModelGroupByModel(String brand) {
        return carModels.stream()
                .filter(carModel -> carModel.getBrand().equalsIgnoreCase(brand)) // фильтр по бренду
                .collect(Collectors.groupingBy(CarModelDTO::getModel, Collectors.summingInt(carModel -> 1))); // группировка по модели и количество
    }
}
