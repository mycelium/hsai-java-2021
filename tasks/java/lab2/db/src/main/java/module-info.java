module db {
    requires transitive input;
    requires java.sql;

    exports reader.db;
}