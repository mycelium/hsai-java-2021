package ru.spbstu.telematics.result;

import ru.spbstu.telematics.list.Variable;
import ru.spbstu.telematics.normality.NormalityChecker;
import ru.spbstu.telematics.parameters.Parameters;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ResultProperty {

    private Map<String, Optional<Double>> properties = new TreeMap();
    private boolean isNormal;
    private String name;

    public ResultProperty(Variable<Double> variable) {
        name = variable.getName();
        isNormal = NormalityChecker.isNormal(variable);
        properties.put("max", Parameters.max(variable));
        properties.put("min", Parameters.min(variable));
        properties.put("mean", Parameters.mean(variable));
        properties.put("median", Parameters.median(variable));
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
        json.append("\"isNormal\":");
        json.append(isNormal);
        json.append("}");
        return json.toString();
    }
}
