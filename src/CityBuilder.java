import java.util.ArrayList;
import java.util.List;

/**
 * Created by nwpwr on 10/10/16.
 */
public class CityBuilder {


    /**
     * Do unification process until one item left in city array / Iteration variable is result of unification operations
     **/
    public static void Build(List<Integer> cityList) {

        ArrayList<City> cities = createNeighborList(cityList);


        int iteration = 0;

        while (cities.size() > 1) {

            uniProcess(cities, getJoinedCities(cities));
            iteration++;

        }

        System.out.println("Byteland Unification Result : " + iteration);

    }


    /**
     * Pairing cities for ins. 4 city and 3 relation {0,1,2}
     * <p/>
     * doing operation 2 times
     * 1.op --> Pairing 0 - 1
     * 2.op --> Pairing 2 - 3
     * <p/>
     * adding pair ordinary (first pair has i'th index and second pair has ++i'th index)
     **/
    private static ArrayList<City> getJoinedCities(ArrayList<City> cities) {

        ArrayList<City> joinedList = new ArrayList<>();

        for (City c_item : cities) {

            if (checkExist(joinedList, c_item)) {
                continue;
            }

            Integer pair = null;

            for (int neighbourhood : c_item.getNeigbours()) {

                if (checkPairNeedsUpdate(cities, pair, neighbourhood)) {
                    pair = neighbourhood;
                }
            }

            if (!checkExist(joinedList, pair)) {
                joinedList.add(c_item);
                joinedList.add(getCityWithId(cities, pair));
            }

        }

        return joinedList;

    }


    /**
     * unification process get pairs and check their neighbours for preventing duplication
     * and remove unified element from main array {cities}
     **/
    private static void uniProcess(ArrayList<City> cities, ArrayList<City> connectedList) {

        for (int i = 0; i < connectedList.size(); i++) {

            City first_pair = connectedList.get(i);
            City second_pair = connectedList.get(++i);

            first_pair.getNeigbours().remove(second_pair.getCityId());
            second_pair.getNeigbours().remove(first_pair.getCityId());

            first_pair.getNeigbours().addAll(second_pair.getNeigbours());

            cities.remove(second_pair);

            for (City c_item : cities) {

                if (c_item.getNeigbours().contains(second_pair.getCityId())) {
                    c_item.getNeigbours().remove(second_pair.getCityId());
                    c_item.getNeigbours().add(first_pair.getCityId());

                }
            }

        }


    }

    /**
     * Get city details with city id
     **/
    private static City getCityWithId(ArrayList<City> list, int id) {
        for (City item : list) {
            if (item.getCityId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * check city or id is exist in array
     **/
    private static boolean checkExist(ArrayList<City> list, Object o) {

        boolean found = false;

        for (City c : list) {
            if (o instanceof City) {
                if (c.getCityId() == ((City) o).getCityId()) {
                    found = true;
                }
            } else if (o instanceof Integer) {
                if (c.getCityId() == (int)o) {
                    found = true;
                }
            }
        }

        return found;

    }


    /**
     * Creating neighborhood list based on user input with checking whether if exist or not
     * <p/>
     * Ex : {CityId} : {Neighbour City Id}  -- Input 4 City - Relations = {0,1,2}
     * <p/>
     * Output: {0} : {1} - {1} : {0,2} - {2} : {1,3} - {3} : {2}
     * <p/>
     * # preUnification operation
     **/
    private static ArrayList<City> createNeighborList(List<Integer> linkedCities) {

        ArrayList<City> cities = new ArrayList<>();

        int check_neighbour = 1;

        City city;

        for (int node : linkedCities) {

            int neighbour = check_neighbour++;

            if (checkExist(cities, node)) {
                getCityWithId(cities, node).getNeigbours().add(neighbour);

            } else {
                city = new City();
                city.setCityId(node);
                city.getNeigbours().add(neighbour);
                cities.add(city);
            }


        }

        ArrayList<City> real_cities = new ArrayList<>();

        real_cities.addAll(cities);

        for (City item : cities) {

            for (int n_id : item.getNeigbours()) {

                if (checkExist(cities, n_id)) {
                    real_cities.get(n_id).getNeigbours().add(item.getCityId());
                } else {
                    city = new City();
                    city.setCityId(n_id);
                    city.getNeigbours().add(item.getCityId());
                    real_cities.add(city);
                }
            }
        }

        return real_cities;

    }

    /**
     * check pair is null or not if null pair needs to update
     **/
    private static boolean checkPairNeedsUpdate(ArrayList<City> cities, Integer pair, Integer otherNeigborhood) {

        if (pair == null) {
            return true;
        } else {
            return getDegree(cities, otherNeigborhood) < getDegree(cities, pair);
        }
    }

    private static int getDegree(ArrayList<City> cityList, Integer neigborhood) {
        return getCityWithId(cityList, neigborhood).getNeigbours().size();
    }


}
