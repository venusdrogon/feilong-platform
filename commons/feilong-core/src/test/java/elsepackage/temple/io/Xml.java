/**
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package elsepackage.temple.io;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-20 下午07:31:08
 * @since 1.0
 */
public class Xml{
	// 44.验证DTD
	// /*
	// import java.io.*;
	// import javax.xml.parsers.*;
	// import org.w3c.dom.*;
	// */
	// File xml_dtd = new File(%%1);
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// try {
	// DocumentBuilder builder = factory.newDocumentBuilder();
	// document = builder.parse(xml_dtd);
	// //进行dtd检查
	// factory.setValidating(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// 45.验证Schema
	// /*
	// import javax.xml.*;
	// import javax.xml.transform.stream.*;
	// import javax.xml.validation.*;
	// import org.xml.sax.*;
	// */
	// SchemaFactory factory =
	// SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	// StreamSource ss = new StreamSource(%%1); //"mySchema.xsd"
	// try {
	// Schema schema = factory.newSchema(ss);
	// } catch (SAXException e) {
	// e.printStackTrace();
	// }
	//
}
