package ru.esstu.carmodel;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarModelReader implements FileManagerCarModelService {
    List<CarModelDTO> carModels;

    public CarModelReader() {
        carModels = new ArrayList<CarModelDTO>(); // создаем список
    }

    @Override
    public void load(String fileName) {
        String line;
        String delimiter = ";";  // разделитель

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int count = 1;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                // Создаем объект CarModelDTO и добавляем в список
                if (values.length >= 4) {
                    CarModelDTO carModel = new CarModelDTO(count, values[0], values[1], values[2], values[3]);
                    carModels.add(carModel);
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод получения списка всех моделей
    public List<CarModelDTO> getAllCarModels() {
        return carModels;
    }

    // Метод получения списка всех моделей с указанным брэндом
    public List<CarModelDTO> getAllCarModels(String brand) {
        return carModels.stream()
                .filter(carModel -> carModel.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());  // группировка
    }

    // Метод получения списка всех моделей с указанным моделью
    // Optional позволяет избежать проблем с null, если автомобиль не найден
    public Optional<CarModelDTO> findCarById(int id) {
        return carModels.stream()
                .filter(carModel -> carModel.getId() == id)
                .findFirst(); // возвращает первый результат
    }

    // Метод получения группы моделей и их количество по бренду
    public Map<String, Integer> getCarModelGroupByModel(String brand) {
        return carModels.stream()
                .filter(carModel -> carModel.getBrand().equalsIgnoreCase(brand)) // фильтр по бренду
                .collect(Collectors.groupingBy(CarModelDTO::getModel, Collectors.summingInt(carModel -> 1))); // группировка по модели и количество
    }
}
