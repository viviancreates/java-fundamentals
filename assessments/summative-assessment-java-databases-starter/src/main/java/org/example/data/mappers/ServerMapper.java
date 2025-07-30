package org.example.data.mappers;

import org.example.model.Server;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class ServerMapper {
    public static RowMapper<Server> map() {
        return (rs, rowNum) -> {
            Server server = new Server();
            server.setServerID(rs.getInt("ServerID"));
            server.setFirstName(rs.getString("FirstName"));
            server.setLastName(rs.getString("LastName"));
            server.setHireDate(rs.getDate("HireDate").toLocalDate());

            java.sql.Date termDate = rs.getDate("TermDate");
            if (termDate != null) {
                server.setTermDate(termDate.toLocalDate());
            }

            return server;
        };
    }
}
