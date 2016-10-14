/**
 * Created by nwpwr on 12/10/2016.
 */
public class Validator {

    /**
     * Variables & methods for validating limitations
     **/

    private static final int MAX_SAMPLE_COUNT = 1000;
    private static final int MIN_SAMPLE_COUNT = 1;
    private static final int MIN_CITY_COUNT = 2;
    private static final int MAX_CITY_COUNT = 600;


    public static boolean isSampleLimitationValid(int sampleCount) {

        return sampleCount < MAX_SAMPLE_COUNT && MIN_SAMPLE_COUNT <= sampleCount;

    }

    public static boolean isCityLimitationValid(int cityCount) {

        return MIN_CITY_COUNT <= cityCount && cityCount <= MAX_CITY_COUNT;

    }
}
