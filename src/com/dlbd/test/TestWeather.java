package com.dlbd.test;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import cn.com.WebXml.WeatherWebServiceLocator;
import cn.com.WebXml.WeatherWebServiceSoapStub;

/**
 * 中央气象局天气预报接口
 * 该接口免费用户（同一IP）24小时只能访问70次左右
 * 不能访问频率过快(必须加延时sleep，目前只测试了间隔一秒)，不然会无法正常显示
 * 
 * @author amao
 *
 */
public class TestWeather {

	public static String getWeatherByCity(String city) {
		//拼接天气
		String str = null;
		try {

			WeatherWebServiceLocator locator = new WeatherWebServiceLocator();
			// 调用xxLocator对象的getXXPort()方法生成xxSoapBindingStub对象
			WeatherWebServiceSoapStub stud = (WeatherWebServiceSoapStub) locator.getWeatherWebServiceSoap();
			// 输出当天的天气状况
			String[] weather = stud.getWeatherbyCityName(city);
			// 获取当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("y年M月d日");
			Date nowDate = new Date();
			String time = sdf.format(nowDate);

			// TODO 这边可以根据自己的业务去选择性的编写
			/**
			 * 这是本人所在公司需要获取当天的气象，请求时间不同可能获取的字符串就不同 所以在此进行了三次判断，保证获取的是当天的天气
			 */
			if (weather[6].contains(time.substring(time.indexOf("年") + 1))) {

				// 截取天气状况
				String weatherStatus = weather[6].substring(weather[6].indexOf(" ") + 1);
				// 拼接天气
				str = weather[1] + "：" + weatherStatus + "\n" + weather[5] + " " + weather[7];

			} else if (weather[13].contains(time.substring(time.indexOf("年") + 1))) {

				// 截取天气状况
				String weatherStatus = weather[13].substring(weather[13].indexOf(" ") + 1);
				// 拼接天气
				str = weather[1] + "：" + weatherStatus + "\n" + weather[12] + " " + weather[14];

			} else if (weather[18].contains(time.substring(time.indexOf("年") + 1))) {

				// 截取天气状况
				String weatherStatus = weather[18].substring(weather[18].indexOf(" ") + 1);
				// 拼接天气
				str = weather[1] + "：" + weatherStatus + "\n" + weather[17] + " " + weather[19];
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void main(String[] args) throws RemoteException, ServiceException {
		String weather = getWeatherByCity("大连");
		System.out.println(weather);
	}

}