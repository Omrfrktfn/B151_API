package pojos;

import java.util.List;

public class CountryPojo {

    private String name;
    private List<StatePojo> states;

    public CountryPojo() {
    }

    public CountryPojo(String name, List<StatePojo> states) {
        this.name = name;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StatePojo> getStates() {
        return states;
    }

    public void setStates(List<StatePojo> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "CountryPojo{" +
                "name='" + name + '\'' +
                ", states=" + states +
                '}';
    }

}
