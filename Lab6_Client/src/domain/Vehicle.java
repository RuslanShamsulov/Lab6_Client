package domain;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс VehicleAndParam.Vehicle, экземплярами которого будет заполняться связный список
 */
public class Vehicle implements Comparable<Vehicle>, Serializable {
    private static int CounterOfId = 0;
    private int UnicId = 0;//Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long enginePower; //Поле может быть null, Значение поля должно быть больше 0 // по этому полю будет происходить сортировка коллекции
    private VehicleType type; //Поле может быть null
    private FuelType fuelType; //Поле может быть null
    public static File file = new File("exampleOfJson.json");


    /**
     * Конструктор для класса VehicleAndParam.Vehicle
     *
     * @param name Имя
     * @param coordinates Координаты
     * @param enginePower Мощность двигателя
     * @param type Тип транспорта
     * @param fuelType тип топлива
     */
    public Vehicle(String name, Coordinates coordinates, Long enginePower, VehicleType type, FuelType fuelType) {
        this.name = name;
        this.coordinates = coordinates;
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
    }

    /**
     * пустой конструктор
     */
    public Vehicle() {
    }


    /**
     *
     * @return Имя трансп средства
     */
    public String getName() {
        return name;
    }

    /**
     * сеттер для названия трансп средства
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * геттер для координат
     * @return Координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * сеттер для координат
     * @param coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * геттер для мощности двигателя
     * @return мощность двигателя
     */
    public Long getEnginePower() {
        return enginePower;
    }

    /**
     * сеттер для мощности двигателя
     * @param enginePower
     */
    public void setEnginePower(Long enginePower) {
        this.enginePower = enginePower;
    }

    /**
     * геттер для типа трансп средства
     * @return тип трансп средства
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * сеттер для типа трансп средства
     * @param type
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * геттер для типа топлива
     * @return
     */
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * сеттер для типа топлива
     * @param fuelType
     */
    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * геттер для Id
     * @return
     */
    public int getId() {
        return UnicId;
    }

    /**
     * сеттер для Id
     * @param id
     */
    public void setId(int id) {
        this.UnicId = id;
    }

    /**
     * генератор ID
     * увеличивает статичную переменную класса VehicleAndParam.Vehicle
     * @return возвращает увеличенный на 1 ID
     */
    public int generateID() {
        this.CounterOfId = ++CounterOfId;
        return CounterOfId;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }


    public int compareTo(Vehicle vehicle){

        return (int) (enginePower-vehicle.getEnginePower());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "UnicId=" + UnicId +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", enginePower=" + enginePower +
                ", type=" + type +
                ", fuelType=" + fuelType +
                '}';
    }
}
