import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nwpwr on 11/10/16.
 */
public class App {


    /**
     * App's main method / Check user inputs and do operations with City Builder
     **/
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            HashMap<Integer, List<Integer>> cityInputs = getInputData(reader);

            for (Map.Entry<Integer, List<Integer>> entry : cityInputs.entrySet()) {
                CityBuilder.Build(entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Input Error");
        } catch (NotValidException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Check inputs & limitations / Data is null");

        }

    }


    /**
     * Get user inputs check limitations
     **/
    private static HashMap<Integer, List<Integer>> getInputData(BufferedReader reader) throws IOException, NotValidException {


        try {
            System.out.print("Enter sample count :");
            int sampleCount = Integer.parseInt(reader.readLine());


            if (!Validator.isSampleLimitationValid(sampleCount)) {
                throw new NotValidException("Sample Count Limitation Error");
            }

            HashMap<Integer, List<Integer>> inputMap = new HashMap<>();

            for (int s = 0; s < sampleCount; s++) {

                System.out.print("Enter city count :");
                int cityCount = Integer.parseInt(reader.readLine());

                if (!Validator.isCityLimitationValid(cityCount)) {
                    throw new NotValidException("City Count Limitation Error");
                }

                System.out.print("Enter city relations : ");
                String relations = reader.readLine();
                String[] roads = relations.split(" ", -1);

                List<Integer> n_list = new ArrayList<>();

                for (int index = 0; index < roads.length; index++) {
                    n_list.add(Integer.valueOf(roads[index]));
                }
                inputMap.put(cityCount, n_list);
            }

            return inputMap;


        } catch (IOException io) {
            System.err.println("Check your inputs & Try again!");
        } catch (NotValidException e) {
            System.err.println(e.getMessage());
        }


        return null;

    }

}
