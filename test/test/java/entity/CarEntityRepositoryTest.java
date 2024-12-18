package test.java.entity;

import org.junit.jupiter.api.*;
import ru.esstu.repository.CarEntityRepository;
import ru.esstu.repository.CarModelRepository;
import ru.esstu.repository.DealerRepository;
import ru.esstu.entity.CarEntity;
import ru.esstu.entity.CarModelEntity;
import ru.esstu.entity.DealerEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Для BeforeAll

public class CarEntityRepositoryTest {
    private CarEntityRepository carRepository;
    private CarModelRepository carModelRepository;
    private DealerRepository dealerRepository;

    // Для указания id в тестах
    private CarEntity car;
    private CarModelEntity model;
    private DealerEntity dealer;

    @BeforeAll
    public void setUp() throws SQLException {
        carRepository = new CarEntityRepository();
        carModelRepository = new CarModelRepository();
        dealerRepository = new DealerRepository();

        try (Connection connection = carRepository.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS CarModel (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "brand VARCHAR(255) NOT NULL," +
                        "model VARCHAR(255) NOT NULL)");

                stmt.execute("CREATE TABLE IF NOT EXISTS Dealer (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL)");

                stmt.execute("CREATE TABLE IF NOT EXISTS Car (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "model_id BIGINT NOT NULL," +
                        "dealer_id BIGINT NOT NULL," +
                        "country_origin VARCHAR(255)," +
                        "country_code VARCHAR(10)," +
                        "status VARCHAR(50)," +
                        "configuration VARCHAR(255)," +
                        "color VARCHAR(50)," +
                        "price DECIMAL(10, 2)," +
                        "FOREIGN KEY (model_id) REFERENCES CarModel(id)," +
                        "FOREIGN KEY (dealer_id) REFERENCES Dealer(id))");

                stmt.execute("DELETE FROM Car");
                stmt.execute("DELETE FROM CarModel");
                stmt.execute("DELETE FROM Dealer");
            }
        }
        model = new CarModelEntity();
        model.setBrand("Mercedes");
        model.setModel("MERCEDES-BENZ GLE 400");
        carModelRepository.create(model);

        dealer = new DealerEntity();
        dealer.setName("Главный дилер");
        dealerRepository.create(dealer);
    }

    @Test
    public void testCreateAndRetrieveCarEntity() throws SQLException {
        CarEntity car = new CarEntity();
        car.setCarModel(carModelRepository.getById(model.getId()));
        car.setDealer(dealerRepository.getById(dealer.getId()));
        car.setCountryOrigin("Germany");
        car.setCountryCode("DE");
        car.setStatus("AVAILABLE");
        car.setConfiguration("Luxury");
        car.setColor("White");
        car.setPrice(75000.0);

        carRepository.create(car);

        // Проверка наличия в таблице
        assertNotNull(car.getId());

        this.car = carRepository.getById(car.getId());
        assertNotNull(car);
        assertEquals("Mercedes", this.car.getCarModel().getBrand());
        assertEquals("Главный дилер", this.car.getDealer().getName());
    }

    @Test
    public void testUpdateCarEntity() throws SQLException {
        CarEntity car = new CarEntity();
        car.setCarModel(carModelRepository.getById(model.getId()));
        car.setDealer(dealerRepository.getById(dealer.getId()));
        car.setCountryOrigin("Germany");
        car.setCountryCode("DE");
        car.setStatus("AVAILABLE");
        car.setConfiguration("Luxury");
        car.setColor("White");
        car.setPrice(75000.0);

        carRepository.create(car);

        car.setColor("Red");
        carRepository.update(car);

        CarEntity updatedCar = carRepository.getById(car.getId());
        assertNotNull(updatedCar);
        assertEquals("Red", updatedCar.getColor());
    }

    @Test
    public void testDeleteCarEntity() throws SQLException {
        CarEntity car = new CarEntity();
        car.setCarModel(carModelRepository.getById(model.getId()));
        car.setDealer(dealerRepository.getById(dealer.getId()));
        car.setCountryOrigin("Germany");
        car.setCountryCode("DE");
        car.setStatus("AVAILABLE");
        car.setConfiguration("Luxury");
        car.setColor("White");
        car.setPrice(75000.0);

        carRepository.create(car);

        carRepository.delete(car.getId());
        CarEntity deletedCar = carRepository.getById(car.getId());
        assertNull(deletedCar);
    }
}
