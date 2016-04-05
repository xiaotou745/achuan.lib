package com.wychuan.code.common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TypeConverter {
	private static HashMap<String, HashMap<String, String>> typeDic = new HashMap<String, HashMap<String, String>>();

	static {
		try {
			init();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final String NAME = "name";
	private static final String VALUE = "value";

	@SuppressWarnings("unused")
	private static void init() throws FileNotFoundException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			InputStream inputStream = TypeConverter.class.getClassLoader().getResourceAsStream("datatype.xml");
			Document document = builder.parse(inputStream);

			Element root = document.getDocumentElement();
			if (root == null) {
				throw new FileNotFoundException("could not find file:[datetype.xml] at the classpath");
			}
			NodeList sectionNodes = root.getChildNodes();
			if (sectionNodes == null || sectionNodes.getLength() == 0) {
				return;
			}

			for (int i = 0; i < sectionNodes.getLength(); i++) {
				Node sectionItem = sectionNodes.item(i);
				if (sectionItem == null || sectionItem.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				String sectionName = sectionItem.getAttributes().getNamedItem(VALUE).getNodeValue();
				typeDic.put(sectionName, new HashMap<String, String>());
				NodeList childNodes = sectionItem.getChildNodes();
				if (childNodes == null || childNodes.getLength() == 0) {
					continue;
				}
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node item = childNodes.item(j);
					if (item == null || item.getNodeType() != Node.ELEMENT_NODE) {
						continue;
					}
					String fromType = item.getAttributes().getNamedItem(NAME).getNodeValue();
					String toType = item.getAttributes().getNamedItem(VALUE).getNodeValue();
					HashMap<String, String> mapItems = typeDic.get(sectionName);
					if (!mapItems.containsKey(fromType)) {
						mapItems.put(fromType, toType);
					}
				}
			}
		} catch (FileNotFoundException io) {
			throw io;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String section, String name) {
		if (typeDic.containsKey(section)) {
			HashMap<String, String> hashMap = typeDic.get(section);
			if (hashMap.containsKey(name)) {
				return hashMap.get(name);
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String key : typeDic.keySet()) {
			HashMap<String, String> hashMap = typeDic.get(key);
			System.out.println("-" + key);
			sb.append(key + "\r\n");
			for (String name : hashMap.keySet()) {
				String value = hashMap.get(name);
				System.out.println(String.format("\t[%s]:[%s]", name, value));
				sb.append(String.format("\t[%s]:[%s]\r\n", name, value));
			}
		}
		return sb.toString();
	}
}
