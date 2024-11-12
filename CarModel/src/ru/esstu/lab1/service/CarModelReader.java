package ru.esstu.lab1.service;

import ru.esstu.lab1.util.FileManagerCarModelService;
import ru.esstu.lab1.carmodel.CarModelDTO;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarModelReader {

    public static void loadFromFile(String fileName, List<CarModelDTO> carModels) {
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
}
