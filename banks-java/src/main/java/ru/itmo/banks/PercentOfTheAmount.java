package ru.itmo.banks;

public class PercentOfTheAmount {
    private double lowerBound;
    private double upperBound;
    private double percent;

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

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
