package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import java.util.InputMismatchException;

import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }
   @Test
   public void testAverageWithOneElementArray() {
       // setup input data and expected result
       double[] temperatureSeries = {-1.0};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
       double expResult = -1.0;

       // call tested method
       double actualResult = seriesAnalysis.average();

       // compare expected result with actual result
       assertEquals(expResult, actualResult, 0.00001);
   }
   @Test(expected = IllegalArgumentException.class)
   public void testAverageWithEmptyArray() {
       double[] temperatureSeries = {};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

       // expect exception here
       seriesAnalysis.average();
   }

   @Test
   public void testAverage() {
       double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
       double expResult = 1.0;

       double actualResult = seriesAnalysis.average();

       assertEquals(expResult, actualResult, 0.00001);
   }
    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.deviation();
    }
    @Test
    public void testDeviationWithSingleValue() {
        double[] temperatureSeries = {5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = seriesAnalysis.deviation();
        assertEquals(0.0, result, 0.00001);
    }
    
    @Test
    public void testDeviationWithIdenticalValues() {
        double[] temperatureSeries = {10.0, 10.0, 10.0, 10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double result = seriesAnalysis.deviation();
        assertEquals(0.0, result, 0.00001);
    }
    // ========== MIN & MAX TESTS ==========
    
    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.min();
    }
    
    @Test
    public void testMinMax() {
        double[] temperatureSeries = {5.0, -10.0, 15.0, 0.0, -5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(-10.0, seriesAnalysis.min(), 0.00001);
        assertEquals(15.0, seriesAnalysis.max(), 0.00001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempClosestToZero();
    }
    
    @Test
    public void testFindTempClosestToZeroPositiveBias() {
        double[] temperatureSeries = {-0.2, 0.2, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(0.2, seriesAnalysis.findTempClosestToZero(), 0.00001);
    }
    
    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {9.5, 10.5, 20.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(10.5, seriesAnalysis.findTempClosestToValue(10.0), 0.00001);
    }
    
    @Test
    public void testFindTempsLessThan() {
        double[] temperatureSeries = {1.0, 5.0, 10.0, 15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsLessThen(10.0);
        assertEquals(2, result.length);
        assertArrayEquals(new double[]{1.0, 5.0}, result, 0.00001);
    }
    
    @Test
    public void testFindTempsGreaterThan() {
        double[] temperatureSeries = {1.0, 5.0, 10.0, 15.0, 20.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsGreaterThen(10.0);
        assertEquals(3, result.length);
        assertArrayEquals(new double[]{10.0, 15.0, 20.0}, result, 0.00001);
    }
    
    @Test
    public void testFindTempsInRange() {
        double[] temperatureSeries = {-5.0, 0.0, 5.0, 10.0, 15.0, 20.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsInRange(0.0, 10.0);
        assertEquals(3, result.length);
        assertArrayEquals(new double[]{0.0, 5.0, 10.0}, result, 0.00001);
    }
    
    @Test
    public void testReset() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(2.0, seriesAnalysis.average(), 0.00001);
        seriesAnalysis.reset();
        try {
            seriesAnalysis.average();
            fail("Should throw IllegalArgumentException after reset");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }
    
    @Test
    public void testSortTemps() {
        double[] temperatureSeries = {5.0, 1.0, 3.0, 2.0, 4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.sortTemps();
        assertArrayEquals(new double[]{1.0, 2.0, 3.0, 4.0, 5.0}, result, 0.00001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.summaryStatistics();
    }
    
    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics stats = seriesAnalysis.summaryStatistics();
        
        assertEquals(3.0, stats.getAvgTemp(), 0.00001);
        assertEquals(1.414213, stats.getDevTemp(), 0.00001);
        assertEquals(1.0, stats.getMinTemp(), 0.00001);
        assertEquals(5.0, stats.getMaxTemp(), 0.00001);
    }
    
    
    @Test(expected = InputMismatchException.class)
    public void testAddTempsWithInvalidTemperature() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(10.0, -300.0, 20.0);
    }
    
//     @Test(expected = InputMismatchException.class)
//     public void testConstructorWithInvalidTemperature() {
//         double[] temperatureSeries = {10.0, -300.0, 20.0};
//         new TemperatureSeriesAnalysis(temperatureSeries);
//     }
// }
}