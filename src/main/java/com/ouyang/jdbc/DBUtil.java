package com.ouyang.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBUtil
{
	private static DataSource dataSource;
	static
	{
		Properties properties = new Properties();
		try
		{
			properties.load(DBUtil.class.getClassLoader().getResourceAsStream(
					"jdbc.properties"));
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSoure()
	{
		return dataSource;
	}

	public static Connection getConn()
	{
		try
		{
			return dataSource.getConnection();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Connection connection, Statement st,
			ResultSet resultSet)
	{
		try
		{
			if (connection != null)
			{
				connection.close();
			}
			if(st != null)
			{
				st.close();
			}
			if(resultSet != null)
			{
				resultSet.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
