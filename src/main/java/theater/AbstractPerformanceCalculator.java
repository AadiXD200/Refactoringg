package theater;

/**
 * Calculator for performance-specific amounts and credits.
 */
public abstract class AbstractPerformanceCalculator {
    private final Performance performance;
    private final Play play;

    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public Performance getPerformance() {
        return performance;
    }

    public Play getPlay() {
        return play;
    }

    public static AbstractPerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            case "history":
                return new HistoryCalculator(performance, play);
            case "pastoral":
                return new PastoralCalculator(performance, play);
            default:
                throw new RuntimeException(String.format("unknown type: %s", play.getType()));
        }
    }

    public abstract int amountFor();

    public int volumeCredits() {
        return Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }
}
