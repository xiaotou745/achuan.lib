package com.wychuan.code.conf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wychuan.code.db.DbType;
import com.wychuan.util.StringUtils;

public class DbSettingBuilder {

	private static List<DbSetting> lstDbSettings = new ArrayList<DbSetting>();
	private final static String USER_NAME = "userName";
	private final static String PASSWORD = "password";
	private final static String DBNAME = "dbName";
	private final static String DBSERVER = "dbServer";
	private final static String DBTYPE = "dbType";
	private final static String DBPORT = "dbPort";
	private final static String ID = "id";
	private final static String FILE_PATH = "dbservers.xml";
	private final static String DEFAULT_PORT_SQLSERVER="1433";
	private final static String DEFAULT_PORT_MYSQL = "3306";
	static {
		init();
	}
	
	public static List<DbSetting> getDbSettings(){
		return lstDbSettings;
	}
	
	public static DbSetting getById(String id){
		if(lstDbSettings == null){
			return null;
		}
		for(DbSetting db : lstDbSettings){
			if(id.equals(db.getId())){
				return db;
			}
		}
		return null;
	}
	
	public static List<String> getDbServers(){
		List<String> result = new ArrayList<String>();
		if(lstDbSettings == null){
			return result;
		}
		for(DbSetting dbsetting : lstDbSettings){
			result.add(dbsetting.getDbServer());
		}
		return result;
	}

	private static void init() {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			InputStream inputStream = DbSettingBuilder.class.getClassLoader().getResourceAsStream(FILE_PATH);
			Document document = docBuilder.parse(inputStream);
			Element root = document.getDocumentElement();
			if (root == null) {
				throw new FileNotFoundException("the [dbserver.xml] file not found in the classpath.");
			}
			NodeList childNodes = root.getChildNodes();
			if (childNodes == null || childNodes.getLength() == 0) {
				return;
			}
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node dbserver = childNodes.item(i);
				if (dbserver == null || dbserver.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				NodeList items = dbserver.getChildNodes();
				if (items == null || items.getLength() == 0) {
					continue;
				}
				DbSetting dbSetting = new DbSetting();
				for (int j = 0; j < items.getLength(); j++) {
					Node item = items.item(j);
					if (item == null || item.getNodeType() != Node.ELEMENT_NODE) {
						continue;
					}
					String nodeName = item.getNodeName();
					String value = item.getTextContent();
					switch (nodeName) {
					case ID:
						dbSetting.setId(value);
						break;
					case DBTYPE:
						dbSetting.setDbType(DbType.valueOf(Integer.valueOf(value)));
						break;
					case USER_NAME:
						dbSetting.setUserName(value);
						break;
					case PASSWORD:
						dbSetting.setPassword(value);
						break;
					case DBNAME:
						dbSetting.setDbName(value);
						break;
					case DBSERVER:
						dbSetting.setDbServer(value);
						break;
					case DBPORT:
						dbSetting.setDbPort(value);
						break;
					}
				}
				if(StringUtils.isEmpty(dbSetting.getDbPort())){
					switch(dbSetting.getDbType()){
					case MySql:
						dbSetting.setDbPort(DEFAULT_PORT_MYSQL);
						break;
					case SqlServer:
						dbSetting.setDbPort(DEFAULT_PORT_SQLSERVER);
						break;
					default:
						break;
					}
				}
				lstDbSettings.add(dbSetting);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
