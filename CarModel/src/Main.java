import ru.esstu.dto.CarModelDTO;
import ru.esstu.service.CarModelService;

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

        Optional<CarModelDTO> car = reader.findCarById(5);
        System.out.print("\nПоиск по id: 5: " + car.get());
        //car.ifPresent(System.out::print);

        System.out.println("\nПоиск по модели: BMW: ");
        String brand = "BMW";
        Map<String, Integer> carModels = reader.getCarModelGroupByModel(brand);
        carModels.forEach((model, count) ->
                System.out.println("Модель='" + model + "', Количество='" + count + "'"));
    }
}