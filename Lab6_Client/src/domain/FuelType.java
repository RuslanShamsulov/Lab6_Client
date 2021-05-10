package domain;

import java.io.Serializable;

/**
 * ENUM с вариантами топлива
 */
public enum FuelType implements Serializable {
    GASOLINE,
    KEROSENE,
    ELECTRICITY,
    PLASMA;
}
