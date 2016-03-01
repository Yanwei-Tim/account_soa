package com.tianque.init.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tianque.init.util.ApplicationContextFactory;

public class XmlUtil {

	private static Document doc;

	public static InputStream[] getPermissionInputSteams() throws Exception {
		return getStreamArrayByFileNames(getTextValueByTagName("permission-init-xml"));
	}

	public static InputStream[] getSqlInputStreams() throws Exception {
		return getStreamArrayByFileNames(getTextValueByTagName("sql-file"));
	}

	public static String[] getSystemPropertiesInitClasses() throws Exception {
		return getTextValueByTagName("property-init-class");
	}

	private static InputStream[] getStreamArrayByFileNames(String[] files)
			throws IOException {
		InputStream[] inputStreams = new InputStream[files.length];
		for (int i = 0; i < files.length; i++) {
			Resource resource = ApplicationContextFactory.getInstance()
					.getApplicationContext()
					.getResource("classpath:" + files[i]);
			System.out.println(files[i]);
			inputStreams[i] = resource.getInputStream();
		}
		return inputStreams;
	}

	private static String[] getTextValueByTagName(String tagName)
			throws Exception {
		Resource[] resources = ApplicationContextFactory.getInstance()
				.getApplicationContext()
				.getResources("classpath:tq-plugin.xml");
		List<String> textValues = new ArrayList<String>();
		for (Resource resource : resources) {
			if (resource.exists()) {
				String propertyValue = read(resource.getInputStream(), tagName);
				if (null != propertyValue && !"".equals(propertyValue)) {
					textValues.add(propertyValue);
				}
			}
		}
		String[] textArray = new String[textValues.size()];
		return textValues.toArray(textArray);
	}

	private static String read(InputStream file, String property)
			throws Exception {
		Document doc = getDocument(file);
		Element root = doc.getDocumentElement();
		if (root == null)
			return null;
		NodeList pluginNodes = root.getChildNodes();
		if (pluginNodes == null)
			return null;
		for (int i = 0; i < pluginNodes.getLength(); i++) {
			Node childNode = pluginNodes.item(i);
			if (childNode != null
					&& childNode.getNodeType() == Node.ELEMENT_NODE
					&& property.equals(childNode.getNodeName())) {
				return childNode.getTextContent();
			}
		}
		return null;
	}

	public static Document getDocument(InputStream file)
			throws ParserConfigurationException, FileNotFoundException,
			SAXException, IOException {
		if (doc == null) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.parse(file);
		}
		return doc;
	}
}
