package com.ouyang.jdbc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class Db2Java
{
	private static Properties properties;
	private static final String dbName = "btserver";
	private static final String packageInfo = "com.";
	private static final String packageName = "/src/main/java/com/";
	
	private static final Logger log = Logger.getLogger(Db2Java.class);

	static
	{
		properties = new Properties();
		try
		{
			properties.load(Db2Java.class.getClassLoader().getResourceAsStream(
					"db2java.properties"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		List<String> list = getTableNames();
		for (String string : list)
		{
			creatEntry(string);
			creatDao(string);
			creatDaoImpl(string);
			creatService(string);
			creatServiceImpl(string);
		}
		
		log.info("更新文件成功！");
		
	}
	
	private static String getEntryStr(String tableName)
	{
		Map<String, String> map = getBeanMap(tableName);
		Set<String> keySet = map.keySet();
		StringBuilder sb = new StringBuilder("package ");
		sb.append(packageInfo).append("entity;\r\n\r\n");
		boolean noDate = true;
		for (String key : keySet)
		{
			if("Date".equals(map.get(key)) && noDate)
			{
				sb.append("import java.util.Date;\r\n");
				noDate = false;
			}
		}
		sb.append("import com.ouyang.common.annotation.Entry;\r\n\r\n");
		sb.append("@Entry\r\n").append("public class ").append(upperFirestChar(tableName)).append("\r\n{\r\n");
		sb.append(getFieldStr(map)).append("\r\n");
		sb.append(getMethodStr(map)).append("\r\n}");
		return sb.toString();
	}
	
	/**
	 * 根据字段得到javabean的map
	 * @param tableName
	 * @return Map<String, String> (filed,type)
	 */
	private static Map<String, String> getBeanMap(String tableName)
	{
		Map<String, String> map = getColumnNames(tableName);
		Map<String, String> result = new HashMap<>();
		Set<String> keySet = map.keySet();
		for (String key : keySet)
		{
			String filed = key;
			String type = typeTrans(map.get(key));
			if(filed.contains("_"))
			{
				filed = filed.substring(0,filed.lastIndexOf("_"));
				type = upperFirestChar(filed);
			}
			
			if(filed.contains("time"))
			{
				type = "String";
			}
			
			result.put(filed, type);
		}
		return result;
	}

	/**
	 * 生成entity目录和java文件
	 * @param tableName
	 */
	private static void creatEntry(String tableName)
	{
		StringBuilder path = new StringBuilder(System.getProperty("user.dir")).append(packageName).append("entity");
		File filePath = new File(path.toString());
		if(!filePath.exists())
		{
			filePath.mkdirs();
		}
		File file = new File(path.toString(), upperFirestChar(tableName) + ".java");
		try
		{
			FileWriter fw = new FileWriter(file);
			fw.write(getEntryStr(tableName));
			fw.flush();
			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成dao目录和java文件
	 * @param tableName
	 */
	private static void creatDao(String tableName)
	{
		StringBuilder path = new StringBuilder(System.getProperty("user.dir")).append(packageName).append("dao");
		File filePath = new File(path.toString());
		if(!filePath.exists())
		{
			filePath.mkdirs();
		}
		File file = new File(path.toString(), upperFirestChar(tableName) + "Dao.java");
		if(!file.exists())
		{
			try
			{
				String entryName = upperFirestChar(tableName);
				FileWriter fw = new FileWriter(file);
				StringBuilder sb = new StringBuilder("package ").append(packageInfo).append("dao;\r\n\r\n");
				sb.append("import ").append(packageInfo).append("common.jdbc.GenericDao;\r\n");
				sb.append("import ").append(packageInfo).append("entry.").append(entryName).append(";\r\n\r\n");
				sb.append("public interface ").append(entryName).append("Dao").append(" extends GenericDao<").append(entryName).append(">\r\n");
				sb.append("{\r\n\r\n}");
				
				fw.write(sb.toString());
				fw.flush();
				fw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 生成daoimpl目录和java文件
	 * @param tableName
	 */
	private static void creatDaoImpl(String tableName)
	{
		StringBuilder path = new StringBuilder(System.getProperty("user.dir")).append(packageName).append("dao/impl");
		File filePath = new File(path.toString());
		if(!filePath.exists())
		{
			filePath.mkdirs();
		}
		File file = new File(path.toString(), upperFirestChar(tableName) + "DaoImpl.java");
		if(!file.exists())
		{
			try
			{
				String entryName = upperFirestChar(tableName);
				FileWriter fw = new FileWriter(file);
				StringBuilder sb = new StringBuilder("package ").append(packageInfo).append("dao.impl;\r\n\r\n");
				sb.append("import ").append(packageInfo).append("common.jdbc.GenericDaoImpl;\r\n");
				sb.append("import ").append(packageInfo).append("dao.").append(entryName).append("Dao;\r\n");
				sb.append("import ").append(packageInfo).append("entity.").append(entryName).append(";\r\n\r\n");
				sb.append("public class ").append(entryName).append("DaoImpl").append(" extends GenericDaoImpl<").append(entryName).append(">").append(" implements ").append(entryName).append("Dao\r\n");
				sb.append("{\r\n\r\n}");
				
				fw.write(sb.toString());
				fw.flush();
				fw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 生成service目录和java文件
	 * @param tableName
	 */
	private static void creatService(String tableName)
	{
		StringBuilder path = new StringBuilder(System.getProperty("user.dir")).append(packageName).append("service");
		File filePath = new File(path.toString());
		if(!filePath.exists())
		{
			filePath.mkdirs();
		}
		File file = new File(path.toString(), upperFirestChar(tableName) + "Service.java");
		if(!file.exists())
		{
			try
			{
				String entryName = upperFirestChar(tableName);
				FileWriter fw = new FileWriter(file);
				StringBuilder sb = new StringBuilder("package ").append(packageInfo).append("service;\r\n\r\n");
				sb.append("import java.util.List;\r\n\r\n");
				sb.append("import ").append(packageInfo).append("entity.").append(entryName).append(";\r\n\r\n");
				sb.append("public interface ").append(entryName).append("Service").append("\r\n");
				sb.append("{\r\n\t");
				sb.append("public boolean add(").append(entryName).append(" ").append(tableName).append(");\r\n\r\n\t");
				sb.append("public boolean delete(Long id);\r\n\r\n\t");
				sb.append("public boolean update(").append(entryName).append(" ").append(tableName).append(");\r\n\r\n\t");
				sb.append("public ").append(entryName).append(" getById(Long id);\r\n\r\n\t");
				sb.append("public List<").append(entryName).append("> list();\r\n}");
				
				fw.write(sb.toString());
				fw.flush();
				fw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 生成serviceimpl目录和java文件
	 * @param tableName
	 */
	private static void creatServiceImpl(String tableName)
	{
		StringBuilder path = new StringBuilder(System.getProperty("user.dir")).append(packageName).append("service/impl");
		File filePath = new File(path.toString());
		if(!filePath.exists())
		{
			filePath.mkdirs();
		}
		File file = new File(path.toString(), upperFirestChar(tableName) + "ServiceImpl.java");
		if(!file.exists())
		{
			try
			{
				String entryName = upperFirestChar(tableName);
				FileWriter fw = new FileWriter(file);
				StringBuilder sb = new StringBuilder("package ").append(packageInfo).append("service.impl;\r\n\r\n");
				sb.append("import java.util.List;\r\n");
				sb.append("import ").append(packageInfo).append("dao.").append(entryName).append("Dao;\r\n");
				sb.append("import ").append(packageInfo).append("dao.impl.").append(entryName).append("DaoImpl;\r\n");
				sb.append("import ").append(packageInfo).append("entity.").append(entryName).append(";\r\n");
				sb.append("import ").append(packageInfo).append("service.").append(entryName).append("Service;\r\n\r\n");
				sb.append("public class ").append(entryName).append("ServiceImpl implements ").append(entryName).append("Service\r\n{");
				sb.append("\r\n\t");
				sb.append("private static ").append(entryName).append("Dao").append(" ").append(tableName).append("Dao").append(" = new ").append(entryName).append("DaoImpl();");
				sb.append("\r\n\t");
				sb.append("@Override");
				sb.append("\r\n\t");
				sb.append("public boolean add(").append(entryName).append(" ").append(tableName).append(")");
				sb.append("\r\n\t");
				sb.append("{");
				sb.append("\r\n\t\t");
				sb.append("return ").append(tableName).append("Dao").append(".add(").append(tableName).append(");");
				sb.append("\r\n\t");
				sb.append("}");
				sb.append("\r\n\t");
				sb.append("@Override");
				sb.append("\r\n\t");
				sb.append("public boolean delete(Long id)");
				sb.append("\r\n\t");
				sb.append("{");
				sb.append("\r\n\t\t");
				sb.append("return ").append(tableName).append("Dao").append(".delete(id);");
				sb.append("\r\n\t");
				sb.append("}");
				sb.append("\r\n\t");
				sb.append("@Override");
				sb.append("\r\n\t");
				sb.append("public boolean update(").append(entryName).append(" ").append(tableName).append(")");
				sb.append("\r\n\t");
				sb.append("{");
				sb.append("\r\n\t\t");
				sb.append("return ").append(tableName).append("Dao").append(".update(").append(tableName).append(");");
				sb.append("\r\n\t");
				sb.append("}");
				sb.append("\r\n\t");
				sb.append("@Override");
				sb.append("\r\n\t");
				sb.append("public ").append(entryName).append(" getById(Long id)");
				sb.append("\r\n\t");
				sb.append("{");
				sb.append("\r\n\t\t");
				sb.append("return ").append(tableName).append("Dao.getById(id);");
				sb.append("\r\n\t");
				sb.append("}");
				sb.append("\r\n\t");
				sb.append("@Override");
				sb.append("\r\n\t");
				sb.append("public List<").append(entryName).append("> list()");
				sb.append("\r\n\t");
				sb.append("{");
				sb.append("\r\n\t\t");
				sb.append("return ").append(tableName).append("Dao.listQuery(null, null, null, null);");
				sb.append("\r\n\t");
				sb.append("}");
				sb.append("\r\n");
				sb.append("}");
				fw.write(sb.toString());
				fw.flush();
				fw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取数据库表信息
	 * 
	 * @return
	 */
	private static List<String> getTableNames()
	{
		List<String> tableList = new LinkedList<String>();

		Connection connection = DBUtil.getConn();
		Statement st = null;
		ResultSet rs = null;
		try
		{
			st = connection.createStatement();
			String sql = properties.getProperty("showTable") + "'" + dbName
					+ "'";
			rs = st.executeQuery(sql);
			while (rs.next())
			{
				tableList.add(rs.getString(1));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.close(connection, st, rs);
		}
		return tableList;
	}

	/**
	 * 读取字段信息
	 * 
	 * @return Map<String, String>  (field,type)
	 */
	private static Map<String, String> getColumnNames(String tabName)
	{
		Map<String, String> colMap = new HashMap<String, String>();

		Connection connection = DBUtil.getConn();
		Statement st = null;
		ResultSet rs = null;
		try
		{
			st = connection.createStatement();
			String sql = properties.getProperty("showColumns") + " " + tabName;
			rs = st.executeQuery(sql);
			while (rs.next())
			{
				colMap.put(rs.getString(1), rs.getString(2));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.close(connection, st, rs);
		}

		return colMap;
	}

	/**
	 * 根据数据库字段类型得到javabean属性类型
	 * @param type
	 * @return
	 */
	private static String typeTrans(String type)
	{
		if (type.contains("tinyint"))
		{
			return "boolean";
		}
		if (type.contains("double"))
		{
			return "Double";
		}
		else if (type.contains("bigint"))
		{
			return "Long";
		}
		else if (type.contains("int"))
		{
			return "Integer";
		}
		else if (type.contains("datetime"))
		{
			return "Date";
		}
		else if (type.contains("binary") || type.contains("blob"))
		{
			return "byte[]";
		}
		else
		{
			return "String";
		}
	}

	/**
	 * 获取方法字符串
	 * 
	 * @param field
	 * @param type
	 * @return
	 */
	private static String getMethodStr(Map<String, String> map)
	{
		StringBuilder sb = new StringBuilder();
		
		Set<String> keySet = map.keySet();
		for (String key : keySet)
		{
			String field = key;
			String type = map.get(key);
			sb.append("\tpublic ").append(type).append(" ");
			if (type.equals("boolean"))
			{
				sb.append(field);
			}
			else
			{
				sb.append("get");
				sb.append(upperFirestChar(field));
			}
			sb.append("()").append("\r\n\t{").append("\r\n\t\treturn this.").append(field).append(";\r\n\t}\r\n");
			sb.append("\tpublic void ");
			if (type.equals("boolean"))
			{
				sb.append(field);
			}
			else
			{
				sb.append("set");
				sb.append(upperFirestChar(field));
			}
			sb.append("(").append(type).append(" ").append(field)
			.append(")\r\n\t{\r\n\t\tthis.").append(field).append("=")
			.append(field).append(";\r\n\t}\r\n");
		}
		return sb.toString();
	}

	/**
	 * 首字母大写
	 * @param src
	 * @return
	 */
	private static String upperFirestChar(String src)
	{
		return src.substring(0, 1).toUpperCase().concat(src.substring(1));
	}

	/**
	 * 获取字段
	 * @param field
	 * @param type
	 * @return
	 */
	private static String getFieldStr(Map<String, String> map)
	{
		StringBuilder sb = new StringBuilder();
		Set<String> keySet = map.keySet();
		for (String key : keySet)
		{
			String field = key;
			String type = map.get(key);
			sb.append("\t").append("private ").append(type).append(" ")
			.append(field).append(";");
			sb.append("\r\n");
		}
		return sb.toString();
	}
}
