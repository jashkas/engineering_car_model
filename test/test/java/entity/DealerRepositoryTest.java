package test.java.entity;

import org.junit.jupiter.api.*;
import ru.esstu.repository.DealerRepository;
import ru.esstu.entity.DealerEntity;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class DealerRepositoryTest {
    private DealerRepository repository;

    @BeforeEach
    public void setUp() throws SQLException {
        repository = new DealerRepository();
    }

    @Test
    public void testCreateAndRetrieveDealer() throws SQLException {
        DealerEntity dealer = new DealerEntity();
        dealer.setName("Test Dealer");
        repository.create(dealer);

        DealerEntity retrievedDealer = repository.getById(1L);
        assertEquals("Test Dealer", retrievedDealer.getName());
    }

    @Test
    public void testUpdateDealer() throws SQLException {
        DealerEntity dealer = repository.getById(1L);
        dealer.setName("Updated Dealer");
        repository.update(dealer);

        DealerEntity updatedDealer = repository.getById(1L);
        assertEquals("Updated Dealer", updatedDealer.getName());
    }

    @Test
    public void testDeleteDealer() throws SQLException {
        repository.delete(1L);
        DealerEntity deletedDealer = repository.getById(1L);
        assertNull(deletedDealer);
    }
}
