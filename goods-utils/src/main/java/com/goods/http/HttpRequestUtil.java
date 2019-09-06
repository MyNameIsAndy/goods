package com.goods.http;

import com.goods.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianshuai
 */
@Component
@Slf4j
public class HttpRequestUtil {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数
     * @return URL 所代表远程资源的响应结果
     * @throws Exception
     */
    public String sendGet(String url, Map<String, String> param) throws Exception {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url;
            String urlSplit = "?";
            if (urlNameString.indexOf("?") > 0) {
                urlSplit = "&";
            }
            if (param != null && !param.isEmpty()) {
                for (String key : param.keySet()) {
                    urlNameString = url + urlSplit + key + "=" + param.get(key);
                }
            }
            log.info("发送地址：" + urlNameString);

            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result.append(line);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                throw e1;
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数
     * @return 所代表远程资源的响应结果
     * @throws Exception
     */
    public String sendPost(String url, Map<String, String> param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String paramStr = "";
            if (param != null && !param.isEmpty()) {
                for (String key : param.keySet()) {
                    paramStr += key + "=" + param.get(key) + "&";
                }
            }
            if (!"".equals(paramStr)) {
                paramStr = paramStr.substring(0, paramStr.length() - 1);
            }
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(paramStr);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                throw e1;
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数为json格式。
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String url, String param) {
        OutputStream out = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        Map<String, String> responseMap = null;

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            //转换为字节数组
            byte[] data = (param).getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json");

            // 开始连接请求
            conn.connect();
            out = conn.getOutputStream();
            // 写入请求的字符串
            out.write(data);
            out.flush();

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                System.out.println("连接成功");
                // 请求返回的数据
                in = conn.getInputStream();
                // 从输入流读取返回内容
                inputStream = conn.getInputStream();
                inputStreamReader = new InputStreamReader(
                        inputStream, "utf-8");
                bufferedReader = new BufferedReader(
                        inputStreamReader);
                String str = null;
                StringBuilder buffer = new StringBuilder();
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                return buffer.toString();
            } else {
                in = conn.getInputStream();
                // 从输入流读取返回内容
                inputStream = conn.getInputStream();
                inputStreamReader = new InputStreamReader(
                        inputStream, "utf-8");
                bufferedReader = new BufferedReader(
                        inputStreamReader);
                String str = null;
                StringBuilder buffer = new StringBuilder();
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                log.warn("-------------------请求返回状态码----------------" + conn.getResponseCode()+"----------return content -----------"+buffer.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {//使用finally块来关闭输出流、输入流
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }


    public String sendPostFile(String url, String fileName) {
        DataInputStream in = null;
        OutputStream out = null;
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        try {
            URL realUrl = new URL(url);
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", "multipart/form-data;");
            out = new DataOutputStream(conn.getOutputStream());
//            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线

            File file = new File(fileName);
            in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }

            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            StringBuilder buffer = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (Exception e) {
            log.error("发送POST请求出现异常！" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 通过拼接的方式构造请求内容，实现参数传输以及文件传输
     *
     * @param actionUrl             访问的服务器URL
     * @param param                 param参数
     * @param absoluteFileName     文件参数
     * @return
     */
    public String postFile(String actionUrl, String param,String absoluteFileName) {
        HttpURLConnection conn = null;
        DataOutputStream outStream = null;
        InputStream in = null;
        InputStream is = null;
        try {
            String BOUNDARY = java.util.UUID.randomUUID().toString();
            String PREFIX = "--", LINEND = "\r\n";
            String MULTIPART_FROM_DATA = "multipart/form-data";
            String CHARSET = "UTF-8";

            conn = getConn(actionUrl);
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
            outStream = new DataOutputStream(conn.getOutputStream());
            // 首先组拼文本类型的参数
            StringBuilder sb = new StringBuilder();
            sb.append(PREFIX).append(BOUNDARY).append(LINEND);
            sb.append("Content-Disposition: form-data; name=\"param\"" + LINEND);
            sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
            sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
            sb.append(LINEND).append(param).append(LINEND);
            outStream.write(sb.toString().getBytes());
            // 发送文件数据
            File file = new File(absoluteFileName);
            sb = new StringBuilder();
            sb.append(PREFIX).append(BOUNDARY).append(LINEND);
            // name是post中传参的键 filename是文件的名称
            sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + LINEND);
            sb.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
            sb.append(LINEND);
            outStream.write(sb.toString().getBytes());

            if (file.exists()){
                //写入文件流
                is = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
            }
            outStream.write(LINEND.getBytes());
            // 请求结束标志
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);

            // 得到响应码
            int res = conn.getResponseCode();
            if (res == 200) {
                in = conn.getInputStream();
                int ch;
                StringBuilder sb2 = new StringBuilder();
                while ((ch = in.read()) != -1) {
                    sb2.append((char) ch);
                }
                return  sb2.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) in.close();
                if (outStream != null) outStream.close();
                if (conn != null) conn.disconnect();
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private HttpURLConnection getConn(String url) throws IOException {
        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(5 * 1000); // 缓存的最长时间
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false); // 不允许使用缓存
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        return conn;
    }


    public static void main(String[] args) {
        String url = "http://172.16.112.113:8881/api/pay-core/balance/queryBalance";
        String fileUrl = "http://localhost:8882/api/pay-third-party/test/file";
        Map<String, String> param = new HashMap();
        param.put("msg", "11");
        System.out.println("=======");
        HttpRequestUtil util = new HttpRequestUtil();
        JsonUtil jsonUtil = new JsonUtil();
        try {
//			sendPost(url, param);
//			sendGet(url, param);
//            util.sendPost(url, jsonUtil.map2Json(param));
//            String s = util.sendPostFile(fileUrl, "E:\\data\\aa6.txt");
            String s = util.postFile(fileUrl,"11","E:\\data\\aa7.txt");
            log.error(s);
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}