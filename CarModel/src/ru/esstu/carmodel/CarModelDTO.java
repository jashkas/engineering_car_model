package ru.esstu.carmodel;

public class CarModelDTO {
    private int id;
    private final String brand;
    private final String model;
    private final String country_origin;
    private final String country_code;

    public CarModelDTO(int id, String brand, String model, String country_origin, String country_code) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.country_origin = country_origin;
        this.country_code = country_code;
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

    public String getCountry_origin() {
        return country_origin;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String toString() {
        return "CarModelDTO{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", country_origin='" + country_origin + '\'' +
                ", country_code='" + country_code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarModelDTO that = (CarModelDTO) o;

        if (id!= that.id) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (country_origin != null ? !country_origin.equals(that.country_origin) : that.country_origin != null) return false;
        if (country_code!= null?!country_code.equals(that.country_code) : that.country_code!= null) return false;
        return false;
    }

}
