package ru.esstu.util;

import ru.esstu.dto.CarModelDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FileManagerCarModelService {
    public abstract void load(String fileName);
    public abstract List<CarModelDTO> getAllCarModels();
    public abstract List<CarModelDTO> getAllCarModels(String brand);
    public abstract Optional<CarModelDTO> findCarById(int id);
    public abstract Map<String, Integer> getCarModelGroupByModel(String brand);
}
