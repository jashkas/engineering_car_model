package ru.esstu.service;
import ru.esstu.carmodel.CarModelDTO;
import ru.esstu.util.FileManagerCarModelService;

import java.util.*;
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

    // Метод возвращает список уникальных марок автомобилей
    public List<String> getUniqueCarBrands() {
        Set<String> uniqueBrands = carModels.stream()
                .map(CarModelDTO::getBrand)
                .collect(Collectors.toSet());
        return new ArrayList<>(uniqueBrands);
    }
}
