package com.wychuan.code.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IniReader {
	private static Map<String, Map<String, String>> sectionsMap = new HashMap<String, Map<String, String>>();

	private static HashMap<String, String> itemsMap = new HashMap<String, String>();

	private static String currentSection = "";

	private static void loadData(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if ("".equals(line)) {
					continue;
				}

				if (line.startsWith("[") && line.endsWith("]")) {
					currentSection = line.substring(1, line.length() - 1);
					itemsMap = null;
					itemsMap = new HashMap<String, String>();
					sectionsMap.put(currentSection, itemsMap);
				} else {
					int indexOfSplit = line.indexOf("=");
					if (indexOfSplit != -1) {
						String key = line.substring(0, indexOfSplit);
						String value = line.substring(indexOfSplit + 1);
						itemsMap.put(key, value);
					}
				}
			}
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
