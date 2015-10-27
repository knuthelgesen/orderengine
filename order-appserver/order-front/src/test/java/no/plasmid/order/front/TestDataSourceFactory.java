package no.plasmid.order.front;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.sql.DataSource;

import org.glassfish.hk2.api.Factory;
import org.h2.jdbcx.JdbcDataSource;

public class TestDataSourceFactory implements Factory<DataSource> {

	private static String CONNECTION_STRING = "jdbc:h2:mem:test;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
	
	private static Connection connection;	
	
	public static void createDatabase() throws Exception {
		Class.forName("org.h2.Driver");
		connection = DriverManager.getConnection(CONNECTION_STRING, "sa", "");
		try {
			connection.prepareStatement("DROP TABLE EVENTS").execute();
			connection.prepareStatement("DROP SEQUENCE seq_global_version").execute();
		} catch (Exception e) {}
		Statement statement = connection.createStatement();
//		statement.executeUpdate("CREATE SEQUENCE seq_global_version");
//		statement.executeUpdate("CREATE TABLE events (global_version bigint, aggregate_id varchar(255), version number, aggregate_type varchar(255), data blob, timestamp timestamp)");
		statement.close();
	}

	public static void dropDatabase() throws Exception {
//		connection.prepareStatement("DROP TABLE EVENTS").execute();
//		connection.prepareStatement("DROP SEQUENCE seq_global_version").execute();
		connection.close();
	}
	
	@Override
	public void dispose(DataSource ds) {
	}

	@Override
	public DataSource provide() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL(CONNECTION_STRING);
		ds.setUser("sa");
		ds.setPassword("");
		return ds;
	}

}
