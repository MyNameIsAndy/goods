package com.goods;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


/**
 * 
 *
 * @author tianshuai
 *
 */
public class DownloadUtil {
	private static Log log = LogFactory.getLog(DownloadUtil.class);
	public static File download(String url,String charsetName,String fileName) throws Exception{
		if(charsetName==null||"".equals(charsetName.trim())){
			charsetName="utf-8";
		}
		if(url.startsWith("https")){
			return httpsDownload(url,charsetName,fileName);
		}else if (url.startsWith("http")) {
			return httpDownload(url,charsetName,fileName);
		}else {
			throw new Exception("不支持的下载协议");
		}
	}
	public synchronized static  File httpDownload(String httpUrl,String charsetName,String fileFullName) throws Exception{
		StringBuffer returnBInfo=new StringBuffer("");
		URL url = null;  
		url = new URL(httpUrl);
		URLConnection conn = url.openConnection();  
		InputStream inStream = conn.getInputStream();  
		InputStreamReader isr = new InputStreamReader(inStream,charsetName);// 字符流
		BufferedReader br = new BufferedReader(isr);// 缓冲流
		File file = null;
		file = new File(fileFullName);
		try(FileOutputStream fos = new FileOutputStream(file,true)){
		String str = null;
		while((str = br.readLine()) != null) {
			fos.write((str+"\r\n").getBytes());
		 }
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
		 br.close();
		 isr.close();
		 inStream.close();
		}
		return file;
	}  
	public static File httpsDownload(String httpsUrl,String charsetName,String fileFullName) throws Exception{
		HttpClient client = new HttpClient(); 
//       client.getState().setCredentials(AuthScope.ANY_HOST, "132.35.87.240",
//     		  new UsernamePasswordCredentials("essmon", "oracle.123"));
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charsetName);
		client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);  //链接超时
		client.getHttpConnectionManager().getParams().setSoTimeout(600000);	//读取超时
		GetMethod get = new GetMethod(httpsUrl);
		get.setDoAuthentication( true );
		int status = client.executeMethod( get );
		InputStream ins  = get.getResponseBodyAsStream();
		//按指定的字符集构建文件流
		BufferedReader br = new BufferedReader(new InputStreamReader(ins,charsetName));
		File file = null;
	    String str = null;
		file = new File(fileFullName);
		try(FileOutputStream fos = new FileOutputStream(file,true)){
		while((str = br.readLine()) != null) {
			fos.write((str+"\r\n").getBytes());
			 }
		}catch(IOException ioe){
				ioe.printStackTrace();
			}finally{
		      br.close();
		      ins.close();
		      get.releaseConnection();
			}
		return file;
	}  
	
	public static void main(String[] args) throws Exception {
		String SERVER_URL="http://59.41.103.98:333/gzdsf/GetSettFile.do?SETT_DATE=20121212&SETT_NO=01&SF_TYPE=S&USER_NAME=abc&MERCHANT_ID=mmm&REQ_TIME=20121213041313&SIGNED_MSG=sss";
	//	SERVER_URL="https://59.41.103.102/gzdsf/GetSettFile.do?SETT_DATE=20121212&SETT_NO=01&SF_TYPE=S&USER_NAME=abc&MERCHANT_ID=mmm&REQ_TIME=20121213041313&SIGNED_MSG=sss";
		//SERVER_URL="https://tgw.baofoo.com/cutpayment/api/backTransRequest?data_content=158819e77e8cdcf6fb5d48c629c456f2b377258e21faad1ec2ebe819a8ad84d0c84e0f216dc3d2f121b25af02d909a1d74d1c051273695cb88cf2d1460fbdab93633cc6bf6d16b175d7730209efb4b4b7ac213951d029ce3853c536e670ecf6971edf8072f76eba90c333bb2deeb2cf8637441c5833896037fa874d1585963961e5ea2f822979ccb7322403b559bb527eec0e126e8ddb4514ddae4290689979dbdef523f7703795a00658cfe1ceaf9871cc76d55861feaa1b9afe8b9be43706c2e50a0d57599a628ec3d83fb80ad53cb32569da721c3b8aaa8c270324ed92194cf40a5d60522b7a3a39feac776dae963b4c1c60bd3c4e3b08295f3186bae451c991c4ffe04a05b14a718fc8b33e691742e5e3970eca865f9cd45c357cf5f89a5bed8f5e31796b3eda83df1e552d73a6deebdfbb6474afca5505441f1ecf1e75afd6068abc6be5e8ecca1e5d46cdf538bd0e55f6e7514e5edc1eed33cb0198446f15b70bde0215fae713fc58ee5784a9da9313fcb01b2fa1b95f0d159535d41ae0aabcdb9f011286f12ad3e9c46c91664f49d947c28b9e5d4aeba99cb1607d1109e9722a780e5a4e9da47d6ca6394191cd8cb0542b18f08ade140fc9195ecec58822ffe7b50c72ec29d5c85966e03f8c5d186cb4b6fafca5f148d941ec18bc169c3c894db0c09a8ed8148ab7226a73b3f02a5d9f419e06ea85e0fe591755e78eda6d13f3e53fbd52f049efd73bc7e516581f3e65a355914918e14be2a4681c7f477456446c933570553bcc0aa725dcad3212d4371d252bfb20f283f132b3581b2e845d17c43f1bc73e80cff66afc8dee964a2100e15928f229d9da64f73fdcc85c8bbbc97b4f08f150ed44c9ca1862175dd472ea4eadc6282531eef513693d69b18384719970fdfecc627444e32a2231c08be58027a5816a6cf3983ce1ffca90a2f11df764674efabf09c71cdeca72fe382253dbd3a683a3012a95d823ab82a3d4fa6e740170efa020f107a59d9da7f3bb4aebb35a1e96551c712faad66bf380811b1192145af64dc34b55c073b89a41ac0cb3b413057a7facddc66a2c9d8727429f5d496f049f610581d8c1eb0949b81db03e8f2c6cbef216a31c0cce1fa98116d5cc2ad814b9a26743233465b59ddfacb730456360bfb536e4127f29bf6bd84a05ed827fee4abb885ca9970460899c6ac11ad62d4a527adecf8f08478472d61c308b10d417c834a8ba959ea1d77933e231d52baff878113003616ba755d4b9103246ac348acdc68c6167f0236ea84230104accb9998c64ec472474f0856c9963301d6ebe7bef0eaa326cefacf795483e64664a45b954fc86afab6ee0631185904af168829360cacbcdbdd3155f5f469f1e19b4497b83cc092ec1d106547c0a193f2e59d7476a61477da95a382c6d5dc0168fdb3fff5a4b06301cff824ec8a03&terminal_id=100000990&data_type=xml&txn_type=0431&txn_sub_type=13&member_id=100000276&version=4.0.0.0";
		System.out.println(download(SERVER_URL,"gbk","aaaa.txt"));
	}
	
}
