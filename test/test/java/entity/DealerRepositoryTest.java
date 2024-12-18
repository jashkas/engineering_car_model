package test.java.entity;

import org.junit.jupiter.api.*;
import ru.esstu.repository.DealerRepository;
import ru.esstu.entity.DealerEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Используется для доступности нестатических методов

public class DealerRepositoryTest {
    private DealerRepository repository;

    @BeforeAll
    public void setUp() throws SQLException {
        repository = new DealerRepository();

        try (Connection connection = repository.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                // Создание таблицы
                stmt.execute("CREATE TABLE IF NOT EXISTS Dealer (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL)");

                // Очистка таблицы
                //stmt.execute("DELETE FROM Dealer");

                // Вставка записей
                stmt.execute("INSERT INTO Dealer (name) VALUES ('Дилер 1'), ('Дилер 2'), ('Дилер 3')");
            }
        }
    }

    @Test
    public void testCreateAndRetrieveDealer() throws SQLException {
        DealerEntity dealer = new DealerEntity();
        dealer.setName("Тест дилер");
        repository.create(dealer);

        // Проверка, что идентификатор корректно установлен
        assertNotNull(dealer.getId());

        DealerEntity retrievedDealer = repository.getById(dealer.getId());
        assertEquals("Тест дилер", retrievedDealer.getName());
    }

    @Test
    public void testUpdateDealer() throws SQLException {
        DealerEntity dealer = new DealerEntity();
        dealer.setName("Тестовый дилер");
        repository.create(dealer);

        // Запись создана и имеет ID
        assertNotNull(dealer.getId());

        dealer.setName("Обновленный дилер");
        repository.update(dealer);

        DealerEntity updatedDealer = repository.getById(dealer.getId());
        assertEquals("Обновленный дилер", updatedDealer.getName());
    }

    @Test
    public void testDeleteDealer() throws SQLException {
        repository.delete(1L);
        DealerEntity deletedDealer = repository.getById(1L);
        assertNull(deletedDealer);
    }
}
