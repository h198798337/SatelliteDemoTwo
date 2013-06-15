package com.ykse.tms.satellite.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.ykse.jaxb.satellite.filminfo.FilmInfoResponse;
import com.ykse.jaxb.satellite.ftp.FtpResponse;
import com.ykse.tms.satellite.api.SatelliteControl;
import com.ykse.tms.satellite.api.SatelliteDevice;
import com.ykse.tms.satellite.api.SatelliteDevice.SatelliteDeviceType;

public class SatelliteApplication {

	protected Shell shlSatellitedemo;
	private Text ip_text;
	private Text cpluuid_text;
	private Text infomation;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SatelliteApplication window = new SatelliteApplication();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSatellitedemo.open();
		shlSatellitedemo.layout();
		while (!shlSatellitedemo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSatellitedemo = new Shell();
		shlSatellitedemo.setSize(607, 435);
		shlSatellitedemo.setText("SatelliteDemo");
		shlSatellitedemo.setLayout(new GridLayout(1, false));
		
		Composite topComposite = new Composite(shlSatellitedemo, SWT.NONE);
		GridLayout gl_topComposite = new GridLayout(4, false);
		gl_topComposite.horizontalSpacing = 0;
		gl_topComposite.marginHeight = 0;
		gl_topComposite.marginWidth = 0;
		gl_topComposite.verticalSpacing = 0;
		topComposite.setLayout(gl_topComposite);
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		topComposite.setBounds(0, 0, 64, 64);
		
		Label lblIp = new Label(topComposite, SWT.NONE);
		lblIp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIp.setBounds(0, 0, 68, 17);
		lblIp.setText("IP地址：");
		
		ip_text = new Text(topComposite, SWT.BORDER);
		GridData gd_ip_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_ip_text.widthHint = 150;
		ip_text.setLayoutData(gd_ip_text);
		
		Label lblCPLuuid = new Label(topComposite, SWT.NONE);
		GridData gd_lblCPLuuid = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblCPLuuid.horizontalIndent = 10;
		lblCPLuuid.setLayoutData(gd_lblCPLuuid);
		lblCPLuuid.setText("CPLUUID：");
		
		cpluuid_text = new Text(topComposite, SWT.BORDER);
		cpluuid_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite middleComposite = new Composite(shlSatellitedemo, SWT.NONE);
		middleComposite.setLayout(new GridLayout(2, false));
		middleComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		middleComposite.setBounds(0, 0, 64, 64);
		
		Composite left_composite = new Composite(middleComposite, SWT.NONE);
		left_composite.setLayout(new GridLayout(1, false));
		left_composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Button btnLinkRequest = new Button(left_composite, SWT.NONE);
		btnLinkRequest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SatelliteDevice satelliteDevice = new SatelliteDevice(ip_text.getText(), SatelliteDeviceType.CRIFST);
				try {
					SatelliteControl satelliteControl = new SatelliteControl(satelliteDevice);
					if(satelliteControl.linkRequest()) {
						infomation.insert("请求成功\n");
					} else {
						infomation.insert("请求失败\n");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLinkRequest.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnLinkRequest.setBounds(0, 0, 88, 29);
		btnLinkRequest.setText("请求连接");
		
		Button btnFilmInfoRequest = new Button(left_composite, SWT.NONE);
		btnFilmInfoRequest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SatelliteDevice satelliteDevice = new SatelliteDevice(ip_text.getText(), SatelliteDeviceType.CRIFST);
				try {
					SatelliteControl satelliteControl = new SatelliteControl(satelliteDevice);
					FilmInfoResponse filmInfoResponse = satelliteControl.filminfoRequest();
					if(filmInfoResponse != null) {
						infomation.insert("影片信息:\n");
						infomation.insert(filmInfoResponse.toXmlString() + "\n");
					} else {
						infomation.insert("获取影片信息失败\n");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFilmInfoRequest.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnFilmInfoRequest.setText("获取影片信息");
		
		Button btnFTPInfo = new Button(left_composite, SWT.NONE);
		btnFTPInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SatelliteDevice satelliteDevice = new SatelliteDevice(ip_text.getText(), SatelliteDeviceType.CRIFST);
				try {
					SatelliteControl satelliteControl = new SatelliteControl(satelliteDevice);
					FtpResponse ftpResponse = satelliteControl.ftpRequest(cpluuid_text.getText());
					if(ftpResponse != null) {
						infomation.insert("影片FTP信息:\n");
						infomation.insert(ftpResponse.toXmlString() + "\n");
					} else {
						infomation.insert("获取影片FTP信息失败\n");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFTPInfo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnFTPInfo.setText("获取影片FTP信息");
		
		Button btnFtpDownloadComplete = new Button(left_composite, SWT.NONE);
		btnFtpDownloadComplete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SatelliteDevice satelliteDevice = new SatelliteDevice(ip_text.getText(), SatelliteDeviceType.CRIFST);
				try {
					SatelliteControl satelliteControl = new SatelliteControl(satelliteDevice);
					if(satelliteControl.setCompleteFlag(cpluuid_text.getText())) {
						infomation.insert("影片下载完成确认成功\n");
					} else {
						infomation.insert("影片下载完成确认失败\n");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFtpDownloadComplete.setText("影片下载完成确认");
		
		Button btnClean = new Button(left_composite, SWT.NONE);
		btnClean.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnClean.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				infomation.setText("");
			}
		});
		btnClean.setText("清空");
		
		Composite right_composite = new Composite(middleComposite, SWT.NONE);
		right_composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		right_composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		infomation = new Text(right_composite, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);

	}
}
