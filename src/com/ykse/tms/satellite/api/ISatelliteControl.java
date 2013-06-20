package com.ykse.tms.satellite.api;

import com.ykse.jaxb.satellite.filminfo.FilmInfoResponse;
import com.ykse.jaxb.satellite.ftp.FtpResponse;

public interface ISatelliteControl {
	public String getXml();
	public boolean linkRequest();
	public FilmInfoResponse filminfoRequest();
	public FtpResponse ftpRequest(String uuid);
	public boolean setCompleteFlag(String uuid);
	
}
