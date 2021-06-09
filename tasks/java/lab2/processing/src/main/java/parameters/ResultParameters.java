package parameters;

import sample.Variable;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ResultParameters {

    private Map<String, Optional<Double>> properties = new TreeMap();
    private String name;

    public ResultParameters(Variable<Double> variable) {
        name = variable.getName();
        properties.put("max", ParametersCalculator.max(variable));
        properties.put("min", ParametersCalculator.min(variable));
        properties.put("mean", ParametersCalculator.mean(variable));
        properties.put("median", ParametersCalculator.median(variable));
    }

    public String toJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{\"name\":\"").append(name).append("\",");
        for (var entry : properties.entrySet()) {
            json.append("\"");
            json.append(entry.getKey());
            json.append("\":");
            json.append(entry.getValue().get());
            json.append(",");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("}");
        return json.toString();
    }
}