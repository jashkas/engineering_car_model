package test.java.entity;

import org.junit.jupiter.api.*;
import ru.esstu.repository.CarModelRepository;
import ru.esstu.entity.CarModelEntity;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class CarModelRepositoryTest {
    private CarModelRepository repository;

    @BeforeEach
    public void setUp() throws SQLException {
        repository = new CarModelRepository();
    }

    @Test
    public void testCreateAndRetrieveCarModel() throws SQLException {
        CarModelEntity model = new CarModelEntity();
        model.setBrand("Toyota");
        model.setModel("Camry");
        repository.create(model);

        CarModelEntity retrievedModel = repository.getById(1L);
        assertEquals("Toyota", retrievedModel.getBrand());
        assertEquals("Camry", retrievedModel.getModel());
    }

    @Test
    public void testUpdateCarModel() throws SQLException {
        CarModelEntity model = repository.getById(1L);
        model.setBrand("Honda");
        repository.update(model);

        CarModelEntity updatedModel = repository.getById(1L);
        assertEquals("Honda", updatedModel.getBrand());
    }

    @Test
    public void testDeleteCarModel() throws SQLException {
        repository.delete(1L);
        CarModelEntity deletedModel = repository.getById(1L);
        assertNull(deletedModel);
    }
}
