package com.himorithm.ignitejdbc.client.configuration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.*;

@Getter
@Slf4j
@Component
public class ConnectionManager {

    public void connection() throws ClassNotFoundException {
        // Open JDBC connection
        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

        log.info("Trying to Connect JDBC");
        try (Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/")) {
            log.info("Connected to server.");

            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE city (id LONG PRIMARY KEY, name VARCHAR) " +
                        "WITH \"template=replicated\"");

                stmt.executeUpdate("CREATE TABLE person (id LONG, name VARCHAR, city_id LONG, " +
                        "PRIMARY KEY (id, city_id)) WITH \"backups=1, affinity_key=city_id\"");

                stmt.executeUpdate("CREATE INDEX on Person (city_id)");
            }

            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO city (id, name) VALUES (?, ?)")) {
                stmt.setLong(1, 1L);
                stmt.setString(2, "Forest Hill");
                stmt.executeUpdate();

                stmt.setLong(1, 2L);
                stmt.setString(2, "Denver");
                stmt.executeUpdate();

                stmt.setLong(1, 3L);
                stmt.setString(2, "St. Petersburg");
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt =
                         conn.prepareStatement("INSERT INTO person (id, name, city_id) values (?, ?, ?)")) {
                stmt.setLong(1, 1L);
                stmt.setString(2, "John Doe");
                stmt.setLong(3, 3L);
                stmt.executeUpdate();

                stmt.setLong(1, 2L);
                stmt.setString(2, "Jane Roe");
                stmt.setLong(3, 2L);
                stmt.executeUpdate();

                stmt.setLong(1, 3L);
                stmt.setString(2, "Mary Major");
                stmt.setLong(3, 1L);
                stmt.executeUpdate();

                stmt.setLong(1, 4L);
                stmt.setString(2, "Richard Miles");
                stmt.setLong(3, 2L);
                stmt.executeUpdate();
            }

            log.info("Populated data.");

            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs =
                             stmt.executeQuery("SELECT p.name, c.name FROM Person p INNER JOIN City c on c.id = p.city_id")) {
                    log.info("Query results:");

                    while (rs.next())
                        System.out.println("Found Data:>>>    " + rs.getString(1) + ", " + rs.getString(2));
                }
            }

            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DROP TABLE Person");
                stmt.executeUpdate("DROP TABLE City");
            }

            log.info("Dropped database objects.");

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}
