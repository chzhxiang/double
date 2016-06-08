package com.kingdee.ebank;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingdee.ebank.util.HttpClientUtil;
import com.kingdee.ebank.util.sign.RSA;
import com.kingdee.ebank.util.sign.SignCore;


public class TestMain {
	private static String url = "http://localhost:8282/ebank-web/api/handle";
	private static String cername = "test_customer_private_pkcs8_key.pem";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
	
	    	Map<String, Object> tradeMap = new HashMap<String, Object>();
	    	
	    	tradeMap.put("customerNo", "121121212");
	    	tradeMap.put("channel", "pab");
	    	tradeMap.put("tradecode", "0002");//
	    	tradeMap.put("bankMerCode", "00203030000000037000");//���пͻ���
	    	
	    	/**
	    	 * һ��ƽ���ӿ�
	    	 */
	    	if("pab".equals(tradeMap.get("channel"))){
		    	//1 ��ҵ�˻�����ѯ [4001]
		    	if("0000".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("account", "11011781161701");
		    	}
		    	//2 ��ҵ�����ʽ�ת[4004]
		    	else if("0001".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", ""+System.currentTimeMillis());
		    		tradeMap.put("amount", "10");
		    		tradeMap.put("currency", "RMB");
		    		tradeMap.put("payAccount", "11011781161701");
			    	tradeMap.put("payAccountName", "SHENFA011781161");
			    	tradeMap.put("receiveBankName", "�й���������");
			    	tradeMap.put("receiveAccount", "11011123456");
			    	tradeMap.put("receiveAccountName", "��ʮ���");
			    	tradeMap.put("mobile", "18617126710");
			    	tradeMap.put("customerAsynurl", "�첽֪ͨ��ַ");
			    	tradeMap.put("remark", "ƽ�����в���");
		    	}
		    	
		    	//3  ��ҵ�������ʽ�ת[4018]
		    	else if("0002".equals(tradeMap.get("tradecode"))){
		    		
		    		tradeMap.put("customerOrderno", "2015"+System.currentTimeMillis());
		    		tradeMap.put("totalcount", "2");
		    		tradeMap.put("totalamount", "10");
		    		tradeMap.put("currency", "RMB");
		    		tradeMap.put("payAccount", "11011781161701");
			    	tradeMap.put("payAccountName", "SHENFA011781161");
			    	
			    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
			    	
			    	Map<String, Object> ext0 = new HashMap<String, Object>();
			    	ext0.put("itemOrderno", ""+System.currentTimeMillis());
			    	ext0.put("itemAmount", "10");
			    	ext0.put("receiveBankName", "ƽ������");
			    	ext0.put("receiveAccount", "6216260000000000548");
			    	ext0.put("receiveAccountName", "PA����67336");
			    	ext0.put("useEx", "������");//�ʽ���;
			    	list.add(ext0);
			    	
			    	Map<String, Object> ext1 = new HashMap<String, Object>();
			    	ext1.put("itemOrderno", ""+System.currentTimeMillis());
			    	ext1.put("itemAmount", "10");
			    	ext1.put("receiveBankCode", "ƽ������");
			    	ext1.put("receiveAccount", "6216260000000000548");
			    	ext1.put("receiveAccountName", "PA����67336");
			    	ext0.put("useEx", "������");//�ʽ���;
			    	list.add(ext1);
			    	tradeMap.put("extra", list);
			    	tradeMap.put("remark", "ƽ�����в���");
		    	}
		    	
		    	//3 ����ת��ָ���ѯ[4005]
		    	else if("0003".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "1443075703449");
		    	}
		    	
		    	//4 ����ת��ָ���ѯ[4015]
		    	else if("0004".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "45416161616158181");
		    	}
		    	
		    	//5  ��ʷ����ѯ[4012]
		    	else if("0005".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("account", "11011781161701");
		    		tradeMap.put("rptDate", "20150910");//���Ʋ�ѯ��ǰ���ڵ�ǰ100���ڵ�
		    	}
		    	
		    	//6  ��ѯ�˻�������ʷ������ϸ[4013]
		    	else if("0006".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("account", "11011781161701");
		    		tradeMap.put("currency", "RMB");//���Ʋ�ѯ��ǰ���ڵ�ǰ100���ڵ�
		    		tradeMap.put("beginDate", "20150922");//��ʼ����
		    		tradeMap.put("endDate", "20150922");//��������
		    		tradeMap.put("pageNo", "1");//��ѯҳ��1����һҳ�����ε���
		    	}
		    	
		    	//7   ������������ӿ�[4047]
		    	else if("0007".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "2015"+System.currentTimeMillis());
		    		tradeMap.put("busiType", "2");// �ͻ�������ǩԼ�������ۺ�����ͨ���ķ������
		    		tradeMap.put("payType", "0");//0����  1����
		    		tradeMap.put("srcAccNo", "11011781161701");//��������Э��ǩԼ�˻�,�����˻�
		    		tradeMap.put("totalcount", "2");
		    		tradeMap.put("totalamount", "20");
		    		tradeMap.put("settleType", "0");//���ֶ�ֻ������Ϊ1�ʵĴ�����Ч0��ʵʱ������ͻ��е��ʴ���Э�飬����ѡ��0������ʵʱ����ʵʱ���ش��۽����1����ʵʱ������ͻ�ֻ����������Э�飬û�е��ʴ���Э�飬��ֻ��ѡ��÷�ʽ���÷�ʽ����������������Ҫ����4048���ײ�ѯ��
		    		tradeMap.put("currency", "RMB");
			    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
			    	
			    	Map<String, Object> ext0 = new HashMap<String, Object>();
			    	ext0.put("itemOrderno", System.currentTimeMillis());
			    	ext0.put("itemAmount", "10");
			    	ext0.put("oppAccNo", "6216260000000000548");
			    	ext0.put("oppAccName", "PA����67336");
			    	list.add(ext0);
			    	
			    	Map<String, Object> ext1 = new HashMap<String, Object>();
			    	ext1.put("itemOrderno", System.currentTimeMillis());
			    	ext1.put("itemAmount", "10");
			    	ext1.put("oppAccNo", "6216260000000000548");
			    	ext1.put("oppAccName", "PA����67336");
			    	list.add(ext1);
			    	tradeMap.put("extra", list);
			    	tradeMap.put("remark", "ƽ�����в���");
		    	}
		    	
		    	//8   �������۽����ѯ�ӿ�[4048]
		    	else if("0008".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "45416161616158181");
		    	}
		    	
		    	//9   �������ת�˽ӿ�[4027]
		    	else if("0009".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "2015"+System.currentTimeMillis());
		    		tradeMap.put("totalcount", "2");
		    		tradeMap.put("totalamount", "10");
		    		tradeMap.put("currency", "RMB");
		    		tradeMap.put("payAccount", "11011781161701");
			    	tradeMap.put("payAccountName", "SHENFA011781161");
			    	
			    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
			    	
			    	Map<String, Object> ext0 = new HashMap<String, Object>();
			    	ext0.put("itemOrderno", System.currentTimeMillis());
			    	ext0.put("itemAmount", "10");
			    	ext0.put("receiveBankName", "ƽ������");
			    	ext0.put("receiveAccount", "6216260000000000548");
			    	ext0.put("receiveAccountName", "PA����67336");
			    	ext0.put("useEx", "������");//�ʽ���;
			    	list.add(ext0);
			    	
			    	Map<String, Object> ext1 = new HashMap<String, Object>();
			    	ext1.put("itemOrderno", System.currentTimeMillis());
			    	ext1.put("itemAmount", "10");
			    	ext1.put("receiveBankCode", "ƽ������");
			    	ext1.put("receiveAccount", "6216260000000000548");
			    	ext1.put("receiveAccountName", "PA����67336");
			    	ext0.put("useEx", "������");//�ʽ���;
			    	list.add(ext1);
			    	tradeMap.put("extra", list);
			    	tradeMap.put("remark", "ƽ�����в���");
		    	}
		    	
		    	//10   ��ǿ��ͻ���Ϣ��֤�ӿ�[400101]
		    	else if("0010".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("account", "11011781161701");
		    		tradeMap.put("accountName", "С��");
		    		tradeMap.put("certType", "1");//֤�����ʹ���
		    		tradeMap.put("certNo", "11011781161701");//֤������
			    	tradeMap.put("mobile", "18617126710");//����Ԥ���ֻ�
		    	}
		    	
		    	
	    	}
	    	
	    	//2  ��������
	    	else if("cmbc".equals(tradeMap.get("channel"))){
	    		//1 ������������
		    	if("0000".equals(tradeMap.get("tradecode"))){
		    		
		    		
		    	}
	    	}
	    	
	    	Gson gson = new Gson();

	    	String jsonstr= gson.toJson(tradeMap);
	    	
	    	System.out.println(jsonstr);
	    	System.out.println("���Ͷ����ţ�"+tradeMap.get("customerOrderno"));
	    	
	    	
//	    	����������ز���
//	    	tradeMap.put("account", "42562112247882215");
//	    	tradeMap.put("TRAN_DATE", new SimpleDateFormat("yyyyMMdd").format( new Date()));
//	    	tradeMap.put("TRAN_TIME", new SimpleDateFormat("HHmmss").format( new Date()));
//	    	tradeMap.put("ORDERNO", String.valueOf(System.currentTimeMillis()));
//	    	tradeMap.put("CURRENCY", "RMB");
//	    	tradeMap.put("ACC_NO", "62221310507254251");
//	    	tradeMap.put("ACC_NAME", "ddd");
//	    	tradeMap.put("TRANS_AMT", "100");
//	    	tradeMap.put("REMARK", "����");
//	    	tradeMap.put("ASYNURL", "http://");
//	    	
//	    	String signstr = SignCore.createLinkString(tradeMap);
	    	String privatekey = getPrivateKey(cername);
	    	String sign = RSA.sign(jsonstr, privatekey, "utf-8");
	    	
	    	
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put("sign", sign);
	    	params.put("data", jsonstr);
	    	
//	    	Map<String, Object> extra = gson.fromJson(jsonstr,new TypeToken<Map<String, Object>>(){}.getType());
//	
//			String bodyStr = HttpClientUtil.sendPostRequest(url,params,"UTF-8" , "UTF-8");

			System.out.println(params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static String getPrivateKey(String path) throws IOException{

		InputStream ips =RSA.class.getResourceAsStream(path);
		
		int i=0; 
		byte[] b = new byte[1024];
		StringBuffer buffer = new StringBuffer();
		while((i=ips.read(b))!=-1){
			String str = new String(b);
			buffer.append(str);
		}
		String privatekey = buffer.toString();
		privatekey = privatekey.replaceAll("-----BEGIN PRIVATE KEY-----", "");
		privatekey = privatekey.replaceAll("-----END PRIVATE KEY-----", "");
	
		System.out.println(privatekey.trim());
		
		return privatekey.trim();

	}
	

}
