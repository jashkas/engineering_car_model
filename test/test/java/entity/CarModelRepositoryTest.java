package test.java.entity;

import org.junit.jupiter.api.*;
import ru.esstu.repository.CarModelRepository;
import ru.esstu.entity.CarModelEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class CarModelRepositoryTest {
    private CarModelRepository repository;

    @BeforeAll
    public void setUpDatabase() throws SQLException {
        repository = new CarModelRepository();

        try (Connection connection = repository.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS CarModel (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "brand VARCHAR(255) NOT NULL," +
                        "model VARCHAR(255) NOT NULL)");

                stmt.execute("DELETE FROM CarModel"); // Ensures table is clean before each test
            }
        }
    }

    @Test
    public void testCreateAndRetrieveCarModel() throws SQLException {
        CarModelEntity model = new CarModelEntity();
        model.setBrand("Toyota");
        model.setModel("Camry");
        repository.create(model);

        assertNotNull(model.getId());

        CarModelEntity retrievedModel = repository.getById(model.getId());
        assertNotNull(retrievedModel);
        assertEquals("Toyota", retrievedModel.getBrand());
        assertEquals("Camry", retrievedModel.getModel());
    }

    @Test
    public void testUpdateCarModel() throws SQLException {
        CarModelEntity model = new CarModelEntity();
        model.setBrand("Nissan");
        model.setModel("Altima");
        repository.create(model);

        assertNotNull(model.getId());

        model.setBrand("Honda");
        repository.update(model);

        CarModelEntity updatedModel = repository.getById(model.getId());
        assertEquals("Honda", updatedModel.getBrand());
    }

    @Test
    public void testDeleteCarModel() throws SQLException {
        CarModelEntity model = new CarModelEntity();
        model.setBrand("BMW");
        model.setModel("X5");
        repository.create(model);

        assertNotNull(model.getId());

        repository.delete(model.getId());

        CarModelEntity deletedModel = repository.getById(model.getId());
        assertNull(deletedModel);
    }
}
