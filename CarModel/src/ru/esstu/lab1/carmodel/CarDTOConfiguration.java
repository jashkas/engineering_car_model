package ru.esstu.lab1.carmodel;

import java.util.Random;

public class CarDTOConfiguration {
    private static final String[] AIRBAGS = {
            "Фронтальные подушки безопасности",
            "Фронтальные и боковые подушки безопасности",
            "Фронтальные, боковые и шторки"
    };

    private static final String[] CLIMATE_CONTROL = {
            "Однозонный климат-контроль",
            "Двухзонный климат-контроль",
            "Многозонный климат-контроль"
    };

    private static final String[] INFOTAINMENT = {
            "Сенсорный экран 8 дюймов",
            "Сенсорный экран 10 дюймов + навигация",
            "Сенсорный экран 12 дюймов + премиум аудиосистема"
    };

    private static final String[] HEADLIGHTS = {
            "Галогенные фары",
            "Светодиодные фары",
            "Ксеноновые фары"
    };

    private static final String[] DRIVE_TYPE = {
            "Передний привод",
            "Задний привод",
            "Полный привод"
    };

    public static String get() {
        Random random = new Random();

        String chosenAirbags = AIRBAGS[random.nextInt(AIRBAGS.length)];
        String chosenClimateControl = CLIMATE_CONTROL[random.nextInt(CLIMATE_CONTROL.length)];
        String chosenInfotainment = INFOTAINMENT[random.nextInt(INFOTAINMENT.length)];
        String chosenHeadlights = HEADLIGHTS[random.nextInt(HEADLIGHTS.length)];
        String chosenDriveType = DRIVE_TYPE[random.nextInt(DRIVE_TYPE.length)];

        return String.format("Комплектация:Подушки безопасности: %s;Климат-контроль: %s;Инфоразвлекательная система: %s;Фары: %s;Привод: %s",
                chosenAirbags, chosenClimateControl, chosenInfotainment, chosenHeadlights, chosenDriveType);
    }
}
