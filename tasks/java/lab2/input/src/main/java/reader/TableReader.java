package reader;

import sample.Variable;

import java.util.List;

public interface TableReader {

    public List<Variable<Double>> readAllDistribution() throws Exception;

}