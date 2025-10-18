package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int ABSOLUTE_ZERO = -273;
    private double[] temperatureSeries;
    private int size;
    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        this.size = 0;

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp < ABSOLUTE_ZERO) {
                throw new IllegalArgumentException("Invalid temp");
            }
        }
        int a = temperatureSeries.length;
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, a);
        this.size = temperatureSeries.length;
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
        double sum = 0;
        for (double temp : temperatureSeries) {
            sum += temp;
        }
        return sum / temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
        throw new IllegalArgumentException("Temperature series is empty");
        }
        double deviation = 0;
        for (double temp : temperatureSeries) {
            double diff = temp - average();
            deviation += diff * diff;
        }
        return Math.sqrt(deviation / temperatureSeries.length);

    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
        double minimum = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (temp < minimum) {
                minimum = temp;
            }
        }
        return minimum;
    }
    

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
        double maximum = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (temp > maximum) {
                maximum = temp;
            }
        }
        return maximum;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
        
        double closest = temperatureSeries[0];
        double minDiff = Math.abs(temperatureSeries[0] - tempValue);
        
        for (double temp : temperatureSeries) {
            double diff = Math.abs(temp - tempValue);
            if (diff < minDiff || (diff == minDiff && temp > closest)) {
                closest = temp;
                minDiff = diff;
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperatureSeries == null) {
            return new double[0];
        }
        
        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                count++;
            }
        }
        double[] result = new double[count];
        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                result[index++] = temp;
            }
        }
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp >= tempValue) {
                count++;
            }
        }
        
        double[] result = new double[count];
        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp >= tempValue) {
                result[index++] = temp;
            }
        }
        return result;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp >= lowerBound && temp <= upperBound) {
                count++;
            }
        }
        
        double[] result = new double[count];
        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp >= lowerBound && temp <= upperBound) {
                result[index++] = temp;
            }
        }
        return result;
    }

    public void reset() {
        this.temperatureSeries = new double[0];
    }

    public double[] sortTemps() {
        double[] sorted = Arrays.copyOf(temperatureSeries, size);
        Arrays.sort(sorted);
        return sorted;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < ABSOLUTE_ZERO) {
                throw new InputMismatchException("Invalid temp");
            }
        }
        int requiredCapacity = size + temps.length;
        if (requiredCapacity > temperatureSeries.length) {
            int newCapacity = temperatureSeries.length;
            if (newCapacity == 0) {
                newCapacity = 1;
            }
            while (newCapacity < requiredCapacity) {
                newCapacity *= 2;
            }
            temperatureSeries = Arrays.copyOf(temperatureSeries, newCapacity);
        }
        for (double temp : temps) {
            temperatureSeries[size++] = temp;
        }
        
        return size;
    }
}
