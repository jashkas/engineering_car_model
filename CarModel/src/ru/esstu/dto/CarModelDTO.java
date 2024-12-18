package ru.esstu.dto;

public class CarModelDTO {
    private int id;
    private final String brand;
    private final String model;
    private final String countryOrigin;
    private final String countryCode;

    public CarModelDTO(int id, String brand, String model, String countryOrigin, String countryCode) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.countryOrigin = countryOrigin;
        this.countryCode = countryCode;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String toString() {
        return "CarModelDTO{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", country_origin='" + countryOrigin + '\'' +
                ", country_code='" + countryCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // Проверка: если объекты идентичны (по ссылке), значит они равны
        if (this == o) return true;

        // Проверка: если переданный объект null или классы не совпадают, они не равны
        if (o == null || getClass() != o.getClass()) return false;

        // Приведение типа для дальнейшего детального сравнения
        CarModelDTO that = (CarModelDTO) o;

        if (id == that.id && brand == null && model == null && countryOrigin == null && countryCode == null) { return true; }
        // Сравнение первичного ключа id. Если он не одинаков, объекты не равны.
        if (id != that.id) return false;

        // Сравнение каждого значимого поля на равенство
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (countryOrigin != null ? !countryOrigin.equals(that.countryOrigin) : that.countryOrigin != null) return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;

        // Если все поля равны, возвращаем true
        return true;
    }
}
