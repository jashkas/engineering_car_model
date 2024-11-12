import ru.esstu.lab1.carmodel.CarModelDTO;
import ru.esstu.lab1.service.CarModelReader;
import ru.esstu.lab1.service.CarModelService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CarModelService reader = new CarModelService();

        List<CarModelDTO> cars = reader.getAllCarModels();
        cars.forEach(System.out::println);

        Optional<CarModelDTO> car = reader.findCarById(817);
        System.out.print("\nПоиск по id: 817: ");
        car.ifPresent(System.out::print);

        String brand = "BMW";
        Map<String, Integer> carModels = reader.getCarModelGroupByModel(brand);
        carModels.forEach((model, count) ->
                System.out.println("Модель='" + model + "', Количество='" + count + "'"));
    }
}