package com.dlbd.test;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import cn.com.WebXml.WeatherWebServiceLocator;
import cn.com.WebXml.WeatherWebServiceSoapStub;

/**
 * �������������Ԥ���ӿ�
 * �ýӿ�����û���ͬһIP��24Сʱֻ�ܷ���70������
 * ���ܷ���Ƶ�ʹ���(�������ʱsleep��Ŀǰֻ�����˼��һ��)����Ȼ���޷�������ʾ
 * 
 * @author amao
 *
 */
public class TestWeather {

	public static String getWeatherByCity(String city) {
		//ƴ������
		String str = null;
		try {

			WeatherWebServiceLocator locator = new WeatherWebServiceLocator();
			// ����xxLocator�����getXXPort()��������xxSoapBindingStub����
			WeatherWebServiceSoapStub stud = (WeatherWebServiceSoapStub) locator.getWeatherWebServiceSoap();
			// ������������״��
			String[] weather = stud.getWeatherbyCityName(city);
			// ��ȡ��ǰʱ��
			SimpleDateFormat sdf = new SimpleDateFormat("y��M��d��");
			Date nowDate = new Date();
			String time = sdf.format(nowDate);

			// TODO ��߿��Ը����Լ���ҵ��ȥѡ���Եı�д
			/**
			 * ���Ǳ������ڹ�˾��Ҫ��ȡ�������������ʱ�䲻ͬ���ܻ�ȡ���ַ����Ͳ�ͬ �����ڴ˽����������жϣ���֤��ȡ���ǵ��������
			 */
			if (weather[6].contains(time.substring(time.indexOf("��") + 1))) {

				// ��ȡ����״��
				String weatherStatus = weather[6].substring(weather[6].indexOf(" ") + 1);
				// ƴ������
				str = weather[1] + "��" + weatherStatus + "\n" + weather[5] + " " + weather[7];

			} else if (weather[13].contains(time.substring(time.indexOf("��") + 1))) {

				// ��ȡ����״��
				String weatherStatus = weather[13].substring(weather[13].indexOf(" ") + 1);
				// ƴ������
				str = weather[1] + "��" + weatherStatus + "\n" + weather[12] + " " + weather[14];

			} else if (weather[18].contains(time.substring(time.indexOf("��") + 1))) {

				// ��ȡ����״��
				String weatherStatus = weather[18].substring(weather[18].indexOf(" ") + 1);
				// ƴ������
				str = weather[1] + "��" + weatherStatus + "\n" + weather[17] + " " + weather[19];
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void main(String[] args) throws RemoteException, ServiceException {
		String weather = getWeatherByCity("����");
		System.out.println(weather);
	}

}