package pres.xgo.fim;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DataSourceTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() throws SQLException {
        assertNotNull(dataSource);

        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection);
            // 如果连接成功，输出连接信息
            System.out.println("DataSource Test: Connection established.");
        }
    }
}
