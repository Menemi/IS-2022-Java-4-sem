package ru.itmo.banks.Clients;

public class Passport {
    public Passport(int series, int number)
    {
        this.series = series;
        this.number = number;
    }

    public int series;
    public int number;

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
