package ru.itmo.banks;

public class PercentOfTheAmount {
    public PercentOfTheAmount(double lowerBound, double upperBound, double percent) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.percent = percent;
    }

    public double lowerBound;
    public double upperBound;
    public double percent;

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
