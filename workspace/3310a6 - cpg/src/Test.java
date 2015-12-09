import java.io.IOException;

/**
 * Created by cpg on 12/8/15.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Setup.main(args);

        /* TEST MAPS */
        Map map = new Map();
        System.out.printf("CITY NAME FOR 3: %s%n", map.getCityName((short)3));
        System.out.printf("CITY CODE FOR 5: %s%n", map.getCityCode((short)5));
        System.out.printf("CITY NUMBER FOR COPENHAGEN: %d%n", map.getCityNumber("Copenhagen"));
        System.out.printf("DISTANCE BETWEEN 3 AND 0: %d%n", map.getRoadDistance((short)3, (short)0));
        System.out.printf("DISTANCE BETWEEN 7 AND 16: %d%n", map.getRoadDistance((short)7, (short)16));
        System.out.printf("DISTANCE BETWEEN 0 AND 0: %d%n", map.getRoadDistance((short)0, (short)0));
        System.out.printf("DISTANCE BETWEEN 1 AND 0: %d%n", map.getRoadDistance((short)1, (short)0));
        System.out.printf("DISTANCE BETWEEN 21 AND 21: %d%n", map.getRoadDistance((short)21, (short)21));
        System.out.printf("DISTANCE BETWEEN 21 AND 0: %d%n", map.getRoadDistance((short)21, (short)0));
        System.out.printf("DISTANCE BETWEEN 21 AND 1: %d%n", map.getRoadDistance((short)21, (short)1));
        System.out.printf("DISTANCE BETWEEN 20 AND 0: %d%n", map.getRoadDistance((short)20, (short)0));
        System.out.printf("DISTANCE BETWEEN 20 AND 1: %d%n", map.getRoadDistance((short)20, (short)1));
        map.finishUp();
    }
}
