package com.goods;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jdom.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

public class XmlUtils {
    private static Log log = LogFactory.getLog(XmlUtils.class);

    /**
     * 瑙ｆ瀽鍒版寚瀹氬璞�
     *
     * @param xml      杈撳叆鎶ユ枃
     * @param nodePath 鏍硅妭鐐�  锛堝畬鏁达級 娉ㄦ剰鍓嶅悗涓嶈兘鏈夆��/鈥濈瓑鐗规畩绗﹀彿
     * @param object   杈撳嚭瀵硅薄
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void getObjFromNode(String xml, String nodePath, Object object) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Document doc = XmlUtils.string2Document(xml);
        List<String> dataList = doc.selectNodes(nodePath);
        Iterator it = dataList.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            HashMap map = new HashMap<String, String>();
            XmlUtils.map(element, map);
            XmlUtils.getObjctFromMap(object, map);
        }
    }

    /**
     * xml转json
     *
     * @param xml
     * @param charse 字符集
     * @return
     */
    public static JSONObject getJsonFromStringXml(String xml, String charse) {
        JSONObject obj = new JSONObject();
        try {
            InputStream is = new ByteArrayInputStream(xml.getBytes(charse));
            SAXBuilder sb = new SAXBuilder(false);
            org.jdom.Document doc = sb.build(is);
            org.jdom.Element root = doc.getRootElement();
            obj.put(root.getName(), iterateElement(root));
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /* * 一个迭代方法
     *
     * @param element
     * : org.jdom.Element
     * @return java.util.Map 实例
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Map iterateElement(org.jdom.Element element) {
        List<org.jdom.Element> jiedian = element.getChildren();
        org.jdom.Element et = null;
        Map obj = new HashMap();
        List list = null;
        for (int i = 0; i < jiedian.size(); i++) {
            list = new LinkedList();
            et = (org.jdom.Element) jiedian.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list);
            }
        }
        return obj;
    }

    /**
     * 鏍规嵁璺緞瑙ｆ瀽鎶ユ枃鍒癿ap
     *
     * @param nodePath 娉ㄦ剰鍓嶅悗涓嶈兘鏈夆��/鈥濈瓑鐗规畩绗﹀彿
     * @param inXml
     * @return map
     */
    public static Map getMapFromString(String inXml, String nodePath) {
        Document doc = string2Document(inXml);
        List responseList = doc.selectNodes(nodePath);
        Iterator it = responseList.iterator();
        HashMap mp = new HashMap();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            mp = map(element, mp);
        }

        return mp;
    }


    /**
     * 鏍规嵁璺緞瑙ｆ瀽鎶ユ枃
     *
     * @param inXml
     * @return
     */
    public static Map getObjectFromString(String inXml, String nodePath) {
        Document doc = string2Document(inXml);
        List responseList = doc.selectNodes(nodePath);
        // 鎶ユ枃澶�
        Iterator it = responseList.iterator();
        HashMap mp = new HashMap();
        while (it.hasNext()) {
            Element errorElement = (Element) it.next();
            mp = firstNoChangeMap(errorElement, mp);
        }

        return mp;
    }


    /**
     * 鏍规嵁璺緞瑙ｆ瀽鎶ユ枃鍒發ist
     *
     * @param nodePath 娉ㄦ剰鍓嶅悗涓嶈兘鏈夆��/鈥濈瓑鐗规畩绗﹀彿
     * @param inXml
     * @return map
     */
    public static List<String> getListFromString(String inXml, String nodePath) {
        List<String> reqlist = new ArrayList<String>();
        Document doc = string2Document(inXml);
        List responseList = doc.selectNodes(nodePath);
        Iterator it = responseList.iterator();
        HashMap mp = new HashMap();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            reqlist = list(element, reqlist);
        }

        return reqlist;
    }

    /**
     * 鏍规嵁璺緞瑙ｆ瀽鎶ユ枃鏁版嵁鍒癿ap鐒跺悗鍐嶄繚瀛樺埌list
     *
     * @param nodePath 娉ㄦ剰鍓嶅悗涓嶈兘鏈夆��/鈥濈瓑鐗规畩绗﹀彿
     * @param inXml
     * @return map
     * @author apple
     * @date 2016-01-08
     */
    public static List<Map<String, String>> getMapListFromXml(String inXml, String nodePath) {
        List<Map<String, String>> reqlist = new ArrayList<Map<String, String>>();
        Document doc = string2Document(inXml);
        List responseList = doc.selectNodes(nodePath);
        Iterator it = responseList.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            HashMap<String, String> map = new HashMap<String, String>();
            mapList(element, reqlist);
        }
        return reqlist;
    }

    /**
     * 灏唀lement濉厖涓簃ap 鑾峰彇鏅�氬瀷鎶ユ枃淇℃伅
     *
     * @param element
     * @param map
     * @return
     */
    public static List<Map<String, String>> mapList(Element element, List<Map<String, String>> reqlist) {
        List list = element.elements();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = new HashMap();
            Element e = (Element) list.get(i);
            if (e.elements() != null && e.elements().size() > 0) {
                map(e, map);
            }
            if (map.get(e.getName()) == null) {
                String key = e.getName();
                map.put(key, e.getText());
            }
            reqlist.add(map);
        }
        return reqlist;
    }

    /**
     * 灏唀lement濉厖涓簃ap 鑾峰彇鏅�氬瀷鎶ユ枃淇℃伅
     *
     * @param element
     * @param map
     * @return
     */
    public static HashMap map(Element element, HashMap map) {
        List list = element.elements();
        for (int i = 0; i < list.size(); i++) {
            Element e = (Element) list.get(i);
            if (e.elements() != null && e.elements().size() > 0) {
                map(e, map);
            }
            if (map.get(e.getName()) == null) {
                String key = e.getName();
                map.put(key, e.getText());
            }
        }
        return map;
    }

    /**
     * 灏唀lement濉厖涓簃ap 鑾峰彇鏅�氬瀷鎶ユ枃淇℃伅
     *
     * @param element
     * @param map
     * @return
     */
    public static HashMap firstNoChangeMap(Element element, HashMap map) {
        List list = element.elements();
        for (int i = 0; i < list.size(); i++) {
            Element e = (Element) list.get(i);
            if (e.elements() != null && e.elements().size() > 0) {
                firstNoChangeMap(e, map);
            }
            if (map.get(e.getName()) == null) {
                String key = e.getName();
                map.put(key, e.getText());
            }
        }
        return map;
    }


    /**
     * 灏唀lement濉厖涓簂ist 鑾峰彇鏅�氬瀷鎶ユ枃淇℃伅
     *
     * @param element
     * @param reqlist
     * @return
     */
    public static List<String> list(Element element, List<String> reqlist) {
        List list = element.elements();
        for (int i = 0; i < list.size(); i++) {
            Element e = (Element) list.get(i);
            if (e.elements() != null && e.elements().size() > 0) {
                list(e, reqlist);
            }
            reqlist.add(e.getText());
        }
        return reqlist;
    }

    /**
     * 灏唀lement濉厖涓簃ap 鑾峰彇閿�煎鍨嬫姤鏂囦俊鎭� 閿�间负鈥渒ey鈥�
     *
     * @param element
     * @param map
     * @return
     */
    public static HashMap mapForItem(Element element, HashMap map) {
        List list = element.elements();
        for (int i = 0; i < list.size(); i++) {
            Element e = (Element) list.get(i);
            if (e.elements() != null && e.elements().size() > 0) {
                mapForItem(e, map);
            }
            if (map.get(e.getName()) == null) {
                String item = e.getStringValue();
                String itemname = e.attributeValue("key");
                String key = itemname.toLowerCase().substring(0, 1) + itemname.replaceAll("-", "_").substring(1);
                map.put(key, item);
            }
        }
        return map;
    }


    /**
     * 瑙ｆ瀽鏁版嵁瀵硅薄
     */

    public static void getObjctFromMap(Object obj, HashMap map)
            throws Exception {
        Class cls = obj.getClass();
        Field fieldlist[] = cls.getDeclaredFields();
        for (int i = 0; i < fieldlist.length; i++) {
            Field field = fieldlist[i];
            String FieldType = field.getType().toString();
            String FieldName = stringFilter(field.getName());
            field.setAccessible(true);//璁剧疆鍙互璁块棶绉佹湁鍙橀噺
            if (FieldType.equalsIgnoreCase("long")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.setLong(obj, 0L);
                else
                    field.setLong(obj, Long.valueOf(
                            map.get(FieldName).toString().trim()).longValue());
            } else if (FieldType.equalsIgnoreCase("short")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.setShort(obj, (short) 0);
                else
                    field.setShort(obj, Short.valueOf(
                            map.get(FieldName).toString().trim()).shortValue());
            } else if (FieldType.equalsIgnoreCase("int")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.setInt(obj, 0);
                else
                    field.setInt(obj, Integer.valueOf(
                            map.get(FieldName).toString().trim()).intValue());
            } else if (FieldType.equalsIgnoreCase("byte")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.setByte(obj, (byte) 0);
                else
                    field.setByte(obj, Byte.valueOf(
                            map.get(FieldName).toString().trim()).byteValue());
            } else if (FieldType.equalsIgnoreCase("float")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.setFloat(obj, 0.0F);
                else
                    field.setFloat(obj, Float.valueOf(
                            map.get(FieldName).toString().trim()).floatValue());
            } else if (FieldType.equalsIgnoreCase("double")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.setDouble(obj, 0.0D);
                else
                    field.setDouble(obj, Double.valueOf(
                            map.get(FieldName).toString().trim())
                            .doubleValue());
            } else if (FieldType.equalsIgnoreCase("boolean")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.setBoolean(obj, false);
                else
                    field.setBoolean(obj, Boolean.valueOf(
                            map.get(FieldName).toString().trim())
                            .booleanValue());
            } else if (FieldType.equalsIgnoreCase("class java.util.Date")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.set(obj, Date.parse("1899-12-31"));
                else
                    field.set(obj, Date.parse(map.get(FieldName).toString().trim()));
            } else if (FieldType.equalsIgnoreCase("class java.lang.String")) {
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null") || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("")) {
                }
                //field.set(obj, "");
                else {
                    field.set(obj, map.get(FieldName).toString().trim());
                }
            } else if (FieldType.equalsIgnoreCase("class java.lang.Integer")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.set(obj, Integer.valueOf(0));
                else
                    field.set(obj, Integer.valueOf(map.get(FieldName)
                            .toString().trim()));
            } else if (FieldType.equalsIgnoreCase("class java.lang.Double")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.set(obj, Double.valueOf(0.0D));
                else
                    field.set(obj, Double.valueOf(map.get(FieldName).toString()
                            .trim()));
            } else if (FieldType.equalsIgnoreCase("class java.lang.Float")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.set(obj, Float.valueOf(0.0F));
                else
                    field.set(obj, Float.valueOf(map.get(FieldName).toString()
                            .trim()));
            } else if (FieldType.equalsIgnoreCase("class java.lang.Short")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.set(obj, Short.valueOf((short) 0));
                else
                    field.set(obj, Short.valueOf(map.get(FieldName).toString()
                            .trim()));
            } else if (FieldType.equalsIgnoreCase("class java.lang.Long")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.set(obj, Long.valueOf(0L));
                else
                    field.set(obj, Long.valueOf(map.get(FieldName).toString()
                            .trim()));
            } else if (FieldType.equalsIgnoreCase("class java.math.BigDecimal")) {
                field = cls.getField(FieldName);
                if (map.get(FieldName) == null
                        || map.get(FieldName).toString().trim()
                        .equalsIgnoreCase("null"))
                    field.set(obj, new BigDecimal(0));
                else
                    field.set(obj, new BigDecimal(map.get(FieldName).toString()
                            .trim()));
            } else {
                log.info((new StringBuilder("涓嶆敮鎸佺殑鏁版嵁绫诲瀷:[")).append(
                        FieldType).append("]").toString());
                field.set(obj, null);
            }
        }

    }


    /**
     * string2Document
     *
     * @param str
     * @return
     */
    public static Document string2Document(String str) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(str);
        } catch (Exception ex) {
            log.error("\n 》》》》》》》》》》 XML格式错误\n" + str + "\n");
        }
        return doc;
    }

    /**
     * doc2String
     *
     * @param document
     * @return
     */
    public static String doc2String(Document document) {
        String s = "";
        try {
            //
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //
            OutputFormat format = new OutputFormat("", true, "UTF-8");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            s = out.toString("UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }


    public static String getValueByNodeName(String xmlStr, String nodeName) {
        //
        if (StringUtil.isNotEmpty(xmlStr)) {
            Document doc = string2Document(xmlStr);
            List nodeList = doc.selectNodes(nodeName);
            Iterator it = nodeList.iterator();
            String elementValue = "";
            while (it.hasNext()) {
                Element element = (Element) it.next();
                elementValue = element.getText();
            }
            return elementValue;
        } else {
            return null;
        }
    }


    public static String stringFilter(String in) {
        String out = "";
        String[] ss = in.split("(?<!^)(?=[A-Z])");
        for (int i = 0; i < ss.length; i++) {
            if (i < ss.length - 1) {
                out += ss[i] + "_";
            } else {
                out += ss[i];
            }
        }
        return out.toUpperCase();
    }

    public static void main(String[] args) {
        String str = "<result><resp_code>BF00116</resp_code><resp_msg>该终端号不存在(terminal_id=2000010734)</resp_msg><txn_sub_type>35</txn_sub_type><member_id></member_id><terminal_id></terminal_id><data_type>xml</data_type><additional_info></additional_info><req_reserved></req_reserved><txn_type>0431</txn_type><version>4.0.0.0</version></result>";
//		 Map<String, String> map = getObjectFromString(str,"packet/transName");
		 /*String s = "AccpHelloWorldHH";
		 String s2 = "";
	        String[] ss = s.split("(?<!^)(?=[A-Z])");
	        for(int i = 0 ;i < ss.length; i ++){
	        	if(i<ss.length-1){
	        		s2+=ss[i]+"_";
	        	}else{
	        		s2+=ss[i];
	        	}
	        }*/

        Document document = string2Document(str);
        log.info(document);


    }
}
