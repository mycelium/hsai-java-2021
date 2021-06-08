//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Random {
//    private final ArrayList<Data> variables;
//    /**
//     * Name of table.
//     */
//    private String name = "Random_data";
//
//    public Random() {
//        variables = new ArrayList<>();
//    }
//
//    public Random(String name) {
//        this.name = name;
//        variables = new ArrayList<>();
//    }
//
//    public RandomData(ArrayList<Data> variables) {
//        this.variables = variables;
//    }
//
//    public RandomData(ArrayList<Data> variables, String name) {
//        this.variables = variables;
//        this.name = name;
//    }
//
//    /**
//     * @return list containing values for one row in tables.
//     */
//    public List<?> generateRow() {
//        return getVariables().stream().map(Data::getDistribution)
//                .map((Distribution d) -> (d.isDiscrete())?
//                        d.generateInt() : d.generateFloat()).
//                        collect(Collectors.toList());
//    }
//
//    /**
//     * @return list of column names.
//     */
//    public List<String> columnNames() {
//        return getVariables().stream().
//                map(Data::getName).
//                collect(Collectors.toList());
//    }
//
//    public RandomData addVariable(Data Data) {
//        variables.add(Data);
//        return this;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public ArrayList<Data> getVariables() {
//        return variables;
//    }
//}
