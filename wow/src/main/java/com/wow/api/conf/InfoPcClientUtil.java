package com.wow.api.conf;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class InfoPcClientUtil {
	
	private static String deviceName;
	private static String ipAddress;
	private static String browserName;
	private static String browserVersion;
	
	public static void setDevice(String device) {
		deviceName = device;
	}
	
	public static String getDevice() {
		return deviceName;
	}
	
	public static void setIpAddress(String ipAddress) {
		InfoPcClientUtil.ipAddress = ipAddress;
	}
	
	public static String getIpAddress() {
		return InfoPcClientUtil.ipAddress;
	}
	
	public static String getClientMACAddress(String clientIp){ 
		String result = "";
        InetAddress ip;
       
        try {
          ip = InetAddress.getLocalHost();
          
          NetworkInterface network = NetworkInterface.getByInetAddress(ip);
          byte[] mac = network.getHardwareAddress();
          
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < mac.length; i++) {
             sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
          }
             result = sb.toString();
       } catch (UnknownHostException e) {
          e.printStackTrace();
       } catch (SocketException e){
          e.printStackTrace();
       }
    
        return result; 
	}
	
	public static String getBrowserName() {
		return browserName;
	}

	public static void setBrowserName(String browserName) {
		InfoPcClientUtil.browserName = browserName;
	}

	public static String getBrowserVersion() {
		return browserVersion;
	}

	public static void setBrowserVersion(String browserVersion) {
		InfoPcClientUtil.browserVersion = browserVersion;
	}
	
}
