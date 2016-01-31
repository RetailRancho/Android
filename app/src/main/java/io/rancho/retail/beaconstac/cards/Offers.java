package io.rancho.retail.beaconstac.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Offers {

    private List<String> Array = new ArrayList<String>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Array
     */
    public List<String> getArray() {
        return Array;
    }

    /**
     *
     * @param Array
     * The Array
     */
    public void setArray(List<String> Array) {
        this.Array = Array;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}