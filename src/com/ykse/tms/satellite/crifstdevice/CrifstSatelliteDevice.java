package com.ykse.tms.satellite.crifstdevice;

import com.ykse.jaxb.satellite.filminfo.Dcp;
import com.ykse.jaxb.satellite.filminfo.FilmInfoResponse;
import com.ykse.jaxb.satellite.ftp.FtpResponse;
import com.ykse.socket.TcpConnect;
import com.ykse.tms.satellite.api.ISatelliteControl;
import com.ykse.tms.satellite.api.SatelliteControl;

public class CrifstSatelliteDevice implements ISatelliteControl{
	private String ipaddr;
	private final int PORT = 20080;
	private int timeoutr = 20000;
	
	private static TcpConnect socket;
	
	public CrifstSatelliteDevice(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	@Override
	public String getXml() {
		// TODO Auto-generated method stub
		System.out.println("正在请求连接...");
		CMDLinkRequest cmdLinkRequest = new CMDLinkRequest(ipaddr, PORT, timeoutr);
		cmdLinkRequest.execute(null);
		if(SatelliteControl.RESULT_STATUS_OK.equals(cmdLinkRequest.getStatus())) {
			System.out.println("请求成功");
			System.out.println("正在请求影片信息...");
			CMDFilmInfoRequest cmdFilmInfoRequest = new CMDFilmInfoRequest(ipaddr, PORT, timeoutr);
			cmdFilmInfoRequest.execute(null);
			FilmInfoResponse filmInfoResponse = cmdFilmInfoRequest.getFilmInfoResponse();
			if(filmInfoResponse != null) {
				System.out.println("获取影片信息成功");
				for (Dcp dcp : filmInfoResponse.getXmlObject().getDcpList().getDcp()) {
					System.out.println("正在请求影片FTP信息...");
					CMDFTPRequest cmdftpRequest = new CMDFTPRequest(ipaddr, PORT, timeoutr);
					cmdftpRequest.execute(dcp.getUuid());
				}
			}
		}
		return null;
	}

	@Override
	public boolean setCompleteFlag(String uuid) {
		// TODO Auto-generated method stub
		System.out.println("正在提交影片下载完成信息...");
		CMDFtpDownLoadComplete cmdFtpDownLoadComplete = new CMDFtpDownLoadComplete(ipaddr, PORT, timeoutr);
		cmdFtpDownLoadComplete.execute(uuid);
		if(cmdFtpDownLoadComplete.getStatus().equals(SatelliteControl.RESULT_STATUS_OK)){
			return true;
		}
		return false;
	}

	@Override
	public boolean linkRequest() {
		// TODO Auto-generated method stub
		CMDLinkRequest cmdLinkRequest = new CMDLinkRequest(ipaddr, PORT, timeoutr);
		if(socket == null)
			socket = cmdLinkRequest.getSocket();
		cmdLinkRequest.execute(null);
		if(SatelliteControl.RESULT_STATUS_OK.equals(cmdLinkRequest.getStatus())) {
			return true;
		}
		return false;
	}

	@Override
	public FilmInfoResponse filminfoRequest() {
		// TODO Auto-generated method stub
		CMDFilmInfoRequest cmdFilmInfoRequest = new CMDFilmInfoRequest(ipaddr, PORT, timeoutr);
		if(socket == null)
			socket = cmdFilmInfoRequest.getSocket();
		cmdFilmInfoRequest.execute(null);
		return cmdFilmInfoRequest.getFilmInfoResponse();
	}

	@Override
	public FtpResponse ftpRequest(String uuid) {
		// TODO Auto-generated method stub
		CMDFTPRequest cmdftpRequest = new CMDFTPRequest(ipaddr, PORT, timeoutr);
		if(socket == null)
			socket = cmdftpRequest.getSocket();
		cmdftpRequest.execute(uuid);
		return cmdftpRequest.getFtpResponse();
	}

	@Override
	public void socketClose() {
		// TODO Auto-generated method stub
		try {
			if(socket != null)
				socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
