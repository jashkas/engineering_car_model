package test.java.entity;

import org.junit.jupiter.api.*;
import ru.esstu.repository.CarEntityRepository;
import ru.esstu.repository.CarModelRepository;
import ru.esstu.repository.DealerRepository;
import ru.esstu.entity.CarEntity;
import ru.esstu.entity.CarModelEntity;
import ru.esstu.entity.DealerEntity;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class CarEntityRepositoryTest {
    private CarEntityRepository carRepository;
    private CarModelRepository carModelRepository;
    private DealerRepository dealerRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        carRepository = new CarEntityRepository();
        carModelRepository = new CarModelRepository();
        dealerRepository = new DealerRepository();

        // Setup the CarModel and Dealer entries for foreign key references
        CarModelEntity model = new CarModelEntity();
        model.setBrand("Mercedes");
        model.setModel("MERCEDES-BENZ GLE 400");
        carModelRepository.create(model);

        DealerEntity dealer = new DealerEntity();
        dealer.setName("Main Dealer");
        dealerRepository.create(dealer);
    }

    @Test
    public void testCreateAndRetrieveCarEntity() throws SQLException {
        CarEntity car = new CarEntity();
        car.setCarModel(carModelRepository.getById(1L));
        car.setDealer(dealerRepository.getById(1L));
        car.setCountryOrigin("Germany");
        car.setCountryCode("DE");
        car.setStatus("AVAILABLE");
        car.setConfiguration("Luxury");
        car.setColor("White");
        car.setPrice(75000.0);

        carRepository.create(car);

        CarEntity retrievedCar = carRepository.getById(1L);
        assertEquals("Mercedes", retrievedCar.getCarModel().getBrand());
        assertEquals("Main Dealer", retrievedCar.getDealer().getName());
    }

    @Test
    public void testUpdateCarEntity() throws SQLException {
        CarEntity car = carRepository.getById(1L);
        car.setColor("Red");
        carRepository.update(car);

        CarEntity updatedCar = carRepository.getById(1L);
        assertEquals("Red", updatedCar.getColor());
    }

    @Test
    public void testDeleteCarEntity() throws SQLException {
        carRepository.delete(1L);
        CarEntity deletedCar = carRepository.getById(1L);
        assertNull(deletedCar);
    }
}
