import ru.esstu.carmodel.CarModelDTO;
import ru.esstu.carmodel.CarModelReader;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CarModelReader reader = new CarModelReader();
        String filepath = "J:\\Bachelor\\3_course\\fundamentals_of_software_engineering\\2 - 18.09.24\\027_DST_CAR_MODEL.csv";
        reader.load(filepath);

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