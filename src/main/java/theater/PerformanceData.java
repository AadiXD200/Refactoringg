package theater;

/**
 * Data class representing calculated information for a performance.
 */
public class PerformanceData {
    private final AbstractPerformanceCalculator performanceCalculator;
    private final int amount;
    private final int volumeCredits;

    public PerformanceData(AbstractPerformanceCalculator performanceCalculator, int amount, int volumeCredits) {
        this.performanceCalculator = performanceCalculator;
        this.amount = amount;
        this.volumeCredits = volumeCredits;
    }

    public int getAudience() {
        return performanceCalculator.getPerformance().getAudience();
    }

    public String getType() {
        return performanceCalculator.getPlay().getType();
    }

    public String getName() {
        return performanceCalculator.getPlay().getName();
    }

    public int getAmount() {
        return amount;
    }

    public Performance getPerformance() {
        return performanceCalculator.getPerformance();
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }
}
