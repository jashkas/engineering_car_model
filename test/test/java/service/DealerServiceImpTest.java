package test.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.esstu.dto.CarDTO;
import ru.esstu.dto.DealerCenterDTO;
import ru.esstu.service.CarModelService;
import ru.esstu.service.DealerCenterService;
import ru.esstu.service.DealerServiceImp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerServiceImpTest {
    private List<CarDTO> fullCarList;

    @BeforeEach
    public void setUp() {
        CarModelService carModelService = new CarModelService("CarModel/src/ru/esstu/027_DST_CAR_MODEL.csv");
        DealerCenterService dealerCenterService = new DealerCenterService();

        // Заполнение диллерского центра разными машинами в количестве 15_000
        DealerCenterDTO dealerCenterDTO = dealerCenterService.createDealerCenter("Dealer", carModelService.getAllCarModels(), 15000);

        // Получение списка абсолютно разных автомобилей
        fullCarList = dealerCenterDTO.getCarList();
    }

    @Test
    public void testMutlithreads() throws InterruptedException {
        // Дилеры с которыми будет проводиться процесс
        DealerCenterDTO dealerCenterDTO1 = new DealerCenterDTO(1L, "Диллер №1", new ArrayList<CarDTO>(), null);
        DealerCenterDTO dealerCenterDTO2 = new DealerCenterDTO(1L, "Продаван", new ArrayList<CarDTO>(), null);
        DealerCenterDTO dealerCenterDTO3 = new DealerCenterDTO(1L, "АВТО.RU", new ArrayList<CarDTO>(), null);

        // Разделение списка на 3 списка
        List<CarDTO> carList1 = fullCarList.subList(0, 1000);
        List<CarDTO> carList2 = fullCarList.subList(1000, 2000);
        List<CarDTO> carList3 = fullCarList.subList(2000, 3000);

        DealerServiceImp dealerServiceImpl = new DealerServiceImp();

        long startTime = System.nanoTime();

        Thread thread1 = new Thread(()->{
            dealerServiceImpl.processCar(dealerCenterDTO1, carList1);
        });
        Thread thread2 = new Thread(()->{
            dealerServiceImpl.processCar(dealerCenterDTO2, carList2);
        });
        Thread thread3 = new Thread(() -> {
            dealerServiceImpl.processCar(dealerCenterDTO3, carList3);
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        long endTime = System.nanoTime();

        // Вычисление времени выполнения
        long duration = endTime - startTime;
        System.out.println("Время выполнения: " + (duration / 1_000_000) + " мс");

        // Сравнение количества авто в шоуруме каждого дилера
        assertEquals(1000, dealerCenterDTO1.getCarsInShowroom().size(), "Диллер №1 должен иметь 1000 машин");
        assertEquals(1000, dealerCenterDTO2.getCarsInShowroom().size(), "Продаван должен иметь 1000 машин");
        assertEquals(1000, dealerCenterDTO3.getCarsInShowroom().size(), "АВТО.RU должен иметь 1000 машин");
    }
}
