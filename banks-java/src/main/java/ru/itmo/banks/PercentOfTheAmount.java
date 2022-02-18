package ru.itmo.banks;

public class PercentOfTheAmount {
    protected double lowerBound;
    protected double upperBound;
    protected double percent;

    public PercentOfTheAmount(double lowerBound, double upperBound, double percent) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.percent = percent;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getPercent() {
        return percent;
    }
}
