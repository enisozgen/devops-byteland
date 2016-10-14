import java.util.HashSet;
import java.util.Set;

/**
 * Created by nwpwr on 10/10/16.
 */
public class City {

    /** Custom City Object **/

    private int cityId;
    private Set<Integer> neigbours;

    public  City()
    {
        neigbours = new HashSet<>();
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Set<Integer> getNeigbours() {
        return neigbours;
    }

}
