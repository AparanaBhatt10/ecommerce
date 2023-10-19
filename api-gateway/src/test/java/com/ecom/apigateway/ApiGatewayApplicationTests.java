package com.ecom.apigateway;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiGatewayApplicationTests {

    @Test
    public void contextLoads() {
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private List<String> getSessionIdsFromDatabase()
            throws SQLException {

        List<String> result = new ArrayList<>();
        ResultSet rs = getResultSet(
                "SELECT * FROM MY_SESSIONS");

        while (rs.next()) {
            result.add(rs.getString("SESSION_ID"));
        }
        return result;
    }

    private List<byte[]> getSessionAttributeBytesFromDb()
            throws SQLException {

        List<byte[]> result = new ArrayList<>();
        ResultSet rs = getResultSet(
                "SELECT * FROM MY_SESSIONS_ATTRIBUTES");

        while (rs.next()) {
            result.add(rs.getBytes("ATTRIBUTE_BYTES"));
        }
        return result;
    }

    private ResultSet getResultSet(String sql)
            throws SQLException {

        Connection conn = DriverManager
                .getConnection("jdbc:h2:mem:sessiondb", "sa", "password");
        Statement stat = conn.createStatement();
        return stat.executeQuery(sql);
    }

    @Test
    public void whenH2DbIsQueried_thenSessionInfoIsEmpty()
            throws SQLException {

        assertEquals(
                0, getSessionIdsFromDatabase().size());
        assertEquals(
                0, getSessionAttributeBytesFromDb().size());
    }

}
