package pl.kuba.managementapp.Chart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataPoint {

    double x; // kilograms
    double y;  // hours
    private String name;
    private String surname;
    private BigDecimal efficiencyFactor;
    private double id;


    public DataPoint(double x, double y, String name, String surname, double id) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.surname = surname;
        this.efficiencyFactor = BigDecimal.valueOf((this.x/this.y)).setScale(2, RoundingMode.HALF_DOWN);;
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public BigDecimal getEfficiencyFactor() {
        return efficiencyFactor;
    }

    public void setEfficiencyFactor(BigDecimal efficiencyFactor) {
        this.efficiencyFactor = efficiencyFactor;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }
}
