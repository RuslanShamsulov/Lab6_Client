package domain;

import java.io.Serializable;

/**
 * класс, реализующий хранение координат
 * @author Shamsulov Ruslan
 */
public class Coordinates implements Serializable {
    private double x; //Значение поля должно быть больше -528
    private float y;

    /**
     *Конструктор
     *
     * @param x координата х
     * @param y координата y
     */
    public Coordinates(double x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * сеттер для значений координат
     * @param x
     * @param y
     */
    public void setValues(double x, float y) {

        this.x = x;
    }

    /**
     *
     * @return координата x
     */
    public double getX() {
        return x;
    }


    /**
     *
     * @return координата y
     */
    public float getY() {
        return y;
    }


    /**
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}


