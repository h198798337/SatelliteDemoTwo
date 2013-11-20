package com.ykse.tms.satellite.api;

import java.util.Scanner;

import com.ykse.tms.satellite.api.SatelliteDevice.SatelliteDeviceType;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String ipaddr = args[0];
		SatelliteDevice satelliteDevice = new SatelliteDevice(ipaddr, SatelliteDeviceType.CRIFST);
		SatelliteControl satelliteControl = new SatelliteControl(satelliteDevice);
		boolean stop = false;
		while(!stop){
			System.out.println("功能选择：1.请求连接   2.影片信息查询   3.影片ftp信息查询   4.退出\n");
			System.out.println("请输入要使用的功能的序号:");
			Scanner sc = new Scanner(System.in);
			int function = Integer.valueOf(sc.next());
			switch (function) {
			case 1:
				satelliteControl.linkRequest();
				break;
			case 2:
				satelliteControl.filminfoRequest();
				break;
			case 3:
				System.out.println("请输入需要查询的uuid:");
				String uuid = sc.next();
				satelliteControl.ftpRequest(uuid);
				break;
			case 4:
				stop=true;
				break;
			default:
				break;
			}
		}
		satelliteControl.socketClose();
	}

}
