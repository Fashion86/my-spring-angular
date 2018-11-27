package com.sample.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.sample.app.util.AppUtil;
import org.springframework.stereotype.Component;

import com.sample.app.model.AppData;
import com.sample.app.model.Employee;
import java.text.SimpleDateFormat;

@Component
public class AppDao {

	public List<Employee> getEmployeeList(Connection conn) throws SQLException {

		String sql = "SELECT * FROM EMP_TEST";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Employee> empList = new ArrayList<Employee>();

		while (rs.next()) {
			String empId = rs.getString("EMP_ID");
			String empName = rs.getString("EMP_NAME");
			int empMobile = rs.getInt("EMP_MOBILE");
			String emdAdd = rs.getString("EMP_ADD");
			Employee emp = new Employee();
			emp.setEmpID(empId);
			emp.setEmpName(empName);
			emp.setPhoneNo(empMobile);
			emp.setAddress(emdAdd);
			empList.add(emp);
		}
		return empList;
	}

	public List<AppData> getAppDataListByCondition(Connection conn, String startDate, String endDate, List<String> houseNum,
                                              List<Map<String, String>> stations, Boolean logs) throws SQLException {

		StringBuffer staionBuf = new StringBuffer();
		StringBuffer channelBuf = new StringBuffer();

		String logsquery = null;
        String stationquery = null;
        String housenumquery = null;
		if (logs) {
            logsquery = "FILETYPE = 'Logs'";
		} else {
            logsquery = "FILETYPE != 'Logs'";
		}

        if(stations.size() > 0) {
            Boolean count = true;
            for(Map<String, String> value: stations) {
//                System.out.println(value.get("station"));
                if (count) {
                    stationquery = "(" + "STATION = '" + value.get("station") + "'" + " AND " + "CHANNEL = '" + value.get("channel") + "'" + ")";
                } else {
                    stationquery = stationquery + " OR " + "(" + "STATION = '" + value.get("station") + "'" + " AND " + "CHANNEL = '" + value.get("channel") + "'" + ")";
                }
                count = false;
            }
            stationquery = " AND " + "(" + stationquery + ")";
        }

        if(houseNum.size() > 0) {
            Boolean count = true;
            for(String value: houseNum) {
                if (count) {
                    housenumquery = "HOUSENUM = '" + value + "'";
                } else {
                    housenumquery = housenumquery + " OR " + "HOUSENUM = '" + value + "'";
                }
                count = false;
            }
            housenumquery = " AND " + "(" + housenumquery + ")";
        }

//		String sql = "select * from ACPMITDE.MAIN_DATA WHERE TO_DATE(ACPMITDE.MAIN_DATA.AIRDATE,'MMDDYY') BETWEEN ? AND ? AND ?";
		String sql = "select * from ACPMITDE.MAIN_DATA WHERE TO_DATE(ACPMITDE.MAIN_DATA.AIRDATE,'MMDDYY') BETWEEN "
				+"TO_DATE(" + "'" + startDate + "'" + ",'YYYY-MM-DD')" + " AND " + "TO_DATE(" + "'" + endDate + "'" + ",'YYYY-MM-DD')"
				+ " AND " + logsquery;
        if (stationquery != null) {
            sql = sql + stationquery;
        }
        if (housenumquery != null) {
            sql = sql + housenumquery;
        }

		System.out.println(sql);

		PreparedStatement pstm = conn.prepareStatement(sql);
//		pstm.setDate(1, java.sql.Date.valueOf(startDate));
		ResultSet rs = pstm.executeQuery();

		List<AppData> appDataList = new ArrayList<AppData>();

		while (rs.next()) {
			String id = rs.getString("ID");
			String filetype = rs.getString("FILETYPE");
			String eventDuration = rs.getString("EVENTDURATION");
			String audioSource = rs.getString("AUDIOSOURCE");
			String programRating = rs.getString("PROGRAMRATING");
			String materialId = rs.getString("MATERIALID");
			String description = rs.getString("DESCRIPTION");
			String showNumber = rs.getString("SHOWNUMBER");
			String secondaryOffset = rs.getString("SECONDARYOFFSET");
			String showSegment = rs.getString("SHOWSEGMENT");
			String breakType = rs.getString("BREAKTYPE");
			String title = rs.getString("TITLE");
			String fileName = rs.getString("FILENAME");
            String station = rs.getString("STATION");
            String channel = rs.getString("CHANNEL");
            String houseNumber = rs.getString("HOUSENUM");

			AppData appData = new AppData();
			appData.setFileType(filetype);
			appData.setEventDuration(eventDuration);
			appData.setAudioSource(audioSource);
			appData.setProgramRating(programRating);
			appData.setMaterialId(materialId);
			appData.setDescription(description);
			appData.setShowNumber(showNumber);
			appData.setSecondaryOffset(secondaryOffset);
			appData.setShowSegment(showSegment);
			appData.setBreakType(breakType);
			appData.setTitle(title);
			appData.setId(id);
			appData.setFileName(fileName);
            appData.setStation(station);
            appData.setChannel(channel);
            appData.setHouseNum(houseNumber);

			appDataList.add(appData);
		}
		return appDataList;
	}

	public List<AppData> getAppDataList(Connection conn, String startDate, String endDate, List<String> houseNum,
			List<String> stations, List<String> channels, String fileType) throws SQLException {
			
		StringBuffer staionBuf = new StringBuffer();
		StringBuffer channelBuf = new StringBuffer();
		
		if(stations.size() == 0) {
			
			staionBuf.append("''");
			
		} else {
		
		for(String value: stations) {		
			staionBuf.append("'");
			staionBuf.append(value);
			staionBuf.append("'");
			staionBuf.append(",");	
		}
		if(staionBuf.length()>0) {
			staionBuf.deleteCharAt(staionBuf.length()-1);
		}
		
		}
		if(channels.size() == 0) {
			
			channelBuf.append("''");
			
		} else {
		for(String value: channels) {		
			channelBuf.append("'");
			channelBuf.append(value);
			channelBuf.append("'");
			channelBuf.append(",");	
		}
		
		if(channelBuf.length()>0) {
			channelBuf.deleteCharAt(channelBuf.length()-1);
		}
		}
		
		String sql = "select * from ACPMITDE.main_data where MODIFIED_DATE_TIME BETWEEN '" + startDate + "' AND '"
				+ endDate + "' OR FILETYPE = '" + fileType +"'" ;
			//	"SELECT EVENTSEQUENCE,SCHEDULEDTIME,EVENTDURATION,PROGRAMRATING,MATERIALID,DESCRIPTION,SHOWNUMBER,SECONDARYOFFSET,SHOWSEGMENT,BREAKTYPE FROM ACPMITDE.main_data	WHERE MODIFIED_DATE_TIME BETWEEN '18-10-31' AND '18-10-31' OR FILETYPE = 'LOGFILE'";
		

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<AppData> appDataList = new ArrayList<AppData>();

		while (rs.next()) {
			String id = rs.getString("ID");
			String eventSequence = rs.getString("EVENTSEQUENCE");
			String scheduledTime = rs.getString("SCHEDULEDTIME");
			String eventDuration = rs.getString("EVENTDURATION");
			String audioSource = rs.getString("AUDIOSOURCE");
			String programRating = rs.getString("PROGRAMRATING");
			String materialId = rs.getString("MATERIALID");
			String description = rs.getString("DESCRIPTION");
			String showNumber = rs.getString("SHOWNUMBER");
			String secondaryOffset = rs.getString("SECONDARYOFFSET");
			String showSegment = rs.getString("SHOWSEGMENT");
			String breakType = rs.getString("BREAKTYPE");

			AppData appData = new AppData();
			appData.setEventSequence(eventSequence);
			appData.setScheduledTime(scheduledTime);
			appData.setEventDuration(eventDuration);
			appData.setAudioSource(audioSource);
			appData.setProgramRating(programRating);
			appData.setMaterialId(materialId);
			appData.setDescription(description);
			appData.setShowNumber(showNumber);
			appData.setSecondaryOffset(secondaryOffset);
			appData.setShowSegment(showSegment);
			appData.setBreakType(breakType);
			appData.setId(id);
			
			appDataList.add(appData);
		}
		return appDataList;
	}

	public List<AppData> getAppDataRow(Connection conn, String filename) throws SQLException {


		List<AppData> appDataList = new ArrayList<AppData>();

		try {
		String sql = "select * from ACPMITDE.MAIN_DATA WHERE MAIN_DATA.FILENAME = '" + filename + "'";
		System.out.println(sql);

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			String eventSequence = rs.getString("EVENTSEQUENCE");
			String scheduledTime = rs.getString("SCHEDULEDTIME");
			String eventDuration = rs.getString("EVENTDURATION");
			String audioSource = rs.getString("AUDIOSOURCE");
			String programRating = rs.getString("PROGRAMRATING");
			String materialId = rs.getString("MATERIALID");
			String description = rs.getString("DESCRIPTION");
			String showNumber = rs.getString("SHOWNUMBER");
			String secondaryOffset = rs.getString("SECONDARYOFFSET");
			String showSegment = rs.getString("SHOWSEGMENT");
			String breakType = rs.getString("BREAKTYPE");

			String fileType = rs.getString("FILETYPE");
			String fileLoc = rs.getString("FILELOC");
			String fileName = rs.getString("FILENAME");
			String houseNum = rs.getString("HOUSENUM");
			String reconNum = rs.getString("RECONNUM");
			String acpRecon = rs.getString("ACP_RECON");
			String lineNum = rs.getString("LINENUM");
			String acpVersion = rs.getString("ACP_VERSION");
			String modifiedBy = rs.getString("MODIFIED_BY");
			String modifiedDateTime = rs.getString("MODIFIED_DATE_TIME");
			String action = rs.getString("ACTION");
			String eventType = rs.getString("EVENTTYPE");
			String logNumber = rs.getString("LOGNUMBER");
			String airDate = rs.getString("AIRDATE");
			String station = rs.getString("STATION");
			String channel = rs.getString("CHANNEL");
			String timeFormat = rs.getString("TIMEFORMAT");
			String version = rs.getString("VERSION");
			String weekday = rs.getString("WEEKDAY");
			String julianDate = rs.getString("JULIANDATE");
			String fill = rs.getString("FILL");
			String timeMode = rs.getString("TIMEMODE");
			String videoSource = rs.getString("VIDEOSOURCE");
			String descriptionVideoMedia = rs.getString("DESCRIPTIVEVIDEOMEDIA");
			String crtcCcdv = rs.getString("CRTC_CCDV");
			String mediaAspectRatio = rs.getString("MEDIAASPECTRATIO");
			String programSource = rs.getString("PROGRAMSOURCE");
			String programType = rs.getString("PROGRAMTYPE");
			String secondarySource = rs.getString("SECONDARYSOURCE");
			String recordType = rs.getString("RECORDTYPE");
			String firstEvent = rs.getString("FIRSTEVENT");
			String lastSegment = rs.getString("LASTSEGMENT");
			String realDurationFlag = rs.getString("REALDURATION_FLAG");
			String breakComments = rs.getString("BREAKCOMMENTS");
			String schedComment = rs.getString("SCHEDCOMMENT");
			String dlComment = rs.getString("DLCOMMENT");
			String describedVideo = rs.getString("DESCRIBEDVIDEO");
//			String contractNumber = rs.getString("CONTRACTNUMBER");
//			String advertiser = rs.getString("ADVERTISER");
			String productName = rs.getString("PRODUCTNAME");
//			String sellingOptionName = rs.getString("SELLINGOPTIONNAMEM");
//			String fixInProgram = rs.getString("FIXINPROGRAM");
//			String fixInBreak = rs.getString("FIXINBREAK");
			String onAir = rs.getString("ON_AIR");
			String empty = rs.getString("EMPTY");
			String dateTime = rs.getString("DATE_TIME");
			String id = rs.getString("ID");
			String s = rs.getString("S");
			String title = rs.getString("TITLE");
			String duration = rs.getString("DURATION");
			String status = rs.getString("STATUS");
			String device = rs.getString("DEVICE");
			String ch = rs.getString("CH");
			String reconcile = rs.getString("RECONCILE");
			String type = rs.getString("TYPE");
			String sec = rs.getString("SEC");

			AppData appData = new AppData();
			appData.setEventSequence(eventSequence);
			appData.setScheduledTime(scheduledTime);
			appData.setEventDuration(eventDuration);
			appData.setAudioSource(audioSource);
			appData.setProgramRating(programRating);
			appData.setMaterialId(materialId);
			appData.setDescription(description);
			appData.setShowNumber(showNumber);
			appData.setSecondaryOffset(secondaryOffset);
			appData.setShowSegment(showSegment);
			appData.setBreakType(breakType);
			appData.setFileType(fileType);
			appData.setFileLoc(fileLoc);
			appData.setFileName(fileName);
			appData.setHouseNum(houseNum);
			appData.setReconNum(reconNum);
			appData.setAcpRecon(acpRecon);
			appData.setLineNum(lineNum);
			appData.setAcpVersion(acpVersion);
			appData.setModifiedBy(modifiedBy);
			appData.setModifiedDateTime(modifiedDateTime);
			appData.setAction(action);
			appData.setEventType(eventType);
			appData.setLogNumber(logNumber);
			appData.setAirDate(airDate);
			appData.setStation(station);
			appData.setChannel(channel);
			appData.setTimeFormat(timeFormat);
			appData.setVersion(version);
			appData.setWeekday(weekday);
			appData.setJulianDate(julianDate);
			appData.setFill(fill);
			appData.setTimeMode(timeMode);
			appData.setVideoSource(videoSource);
			appData.setDescriptionVideoMedia(descriptionVideoMedia);
			appData.setCrtcCcdv(crtcCcdv);
			appData.setMediaAspectRatio(mediaAspectRatio);
			appData.setProgramSource(programSource);
			appData.setProgramType(programType);
			appData.setSecondarySource(secondarySource);
			appData.setRecordType(recordType);
			appData.setFirstEvent(firstEvent);
			appData.setLastSegment(lastSegment);
			appData.setRealDurationFlag(realDurationFlag);
			appData.setBreakComments(breakComments);
			appData.setSchedComment(schedComment);
			appData.setDlComment(dlComment);
			appData.setDescribedVideo(describedVideo);
//			appData.setContractNumber(contractNumber);
//			appData.setAdvertiser(advertiser);
			appData.setProductName(productName);
//			appData.setSellingOptionName(sellingOptionName);
//			appData.setFixInProgram(fixInProgram);
//			appData.setFixInBreak(fixInBreak);
			appData.setOnAir(onAir);
			appData.setEmpty(empty);
			appData.setDateTime(dateTime);
			appData.setId(id);
			appData.setS(s);
			appData.setTitle(title);
			appData.setDuration(duration);
			appData.setStatus(status);
			appData.setDevice(device);
			appData.setCh(ch);
			appData.setReconcile(reconcile);
			appData.setType(type);
			appData.setSec(sec);
			appDataList.add(appData);
		}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			conn.close();
		}
		return appDataList;
	}

	public List<String> updateAppDataRow(Connection conn, ArrayList<Map<String, Object>>  reqMap) throws SQLException {

		List<String> errorList = new ArrayList<String>();
        int index = 0;
		for(Map<String, Object> reqdata: reqMap) {
			Set<String> keys = reqdata.keySet();
			String rowid = AppUtil.getIdCaps(reqdata);
			String newquery = null;

			if(keys.size() > 0) {
				Boolean count = true;
				for(String key: keys) {
					if (key.equals("descriptionVideoMedia")) {
						key = "descriptVideoMedia";
					}
                    if (key.equals("dateTime")) {
                        key = "date_Time";
                    }
                    if (key.equals("onAir")) {
                        key = "on_Air";
                    }
                    if (key.equals("acpRecon")) {
                        key = "acp_Recon";
                    }
                    if (key.equals("descriptionVideoMedia")) {
                        key = "descriptiveVideoMedia";
                    }
                    if (key.equals("acpRecon")) {
                        key = "acp_Recon";
                    }
                    if (key.equals("acpRecon")) {
                        key = "acp_Recon";
                    }
                    if (key.equals("fixInBreak") || key.equals("fixInProgram") || key.equals("sellingOptionName") || key.equals("advertiser") || key.equals("contractNumber")
                    || key.equals("realDurationFlag") || key.equals("crtcCcdv") || key.equals("modifiedDateTime") || key.equals("modifiedBy")
                    || key.equals("acpVersion")|| key.equals("descriptVideoMedia")) {
                        continue;
                    }
					if (count) {
						if(reqdata.get(key) == null) {
							newquery = key + " = " + null;
						} else {
							newquery = key + " = '" + reqdata.get(key) + "'";
						}
					} else {
						if(reqdata.get(key) == null) {
							newquery = newquery + ", " + key + " = " + null;
						} else {
							newquery = newquery + ", " + key + " = '" + reqdata.get(key) + "'";
						}
					}
					count = false;
				}
			}

			try {
                index++;
			    Date modifydate = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
				String format = formatter.format(modifydate);

			    newquery = newquery + ", " + "MODIFIED_DATE_TIME" + " = " + "TO_DATE(" + "'" + format + "'" + ",'DD-MM-YY')";

				String sql = "UPDATE ACPMITDE.MAIN_DATA SET " + newquery + " WHERE ID = " + "'" + rowid + "'";
//				String sql = "UPDATE ACPMITDE.MAIN_DATA SET eventSequence = 'CT' WHERE ID = '5'";
				System.out.println(rowid);
				System.out.println(sql);
				PreparedStatement pstms = conn.prepareStatement(sql);
				try {
					pstms.executeUpdate();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					errorList.add("Error on row" + index +" " + e.getMessage());
				}
				finally {
					System.out.println("finish");
					if (pstms != null) {
						pstms.close();
					}
				}



//
//				while (rs.next()) {
//					String eventSequence = rs.getString("EVENTSEQUENCE");
//					String scheduledTime = rs.getString("SCHEDULEDTIME");
//					String eventDuration = rs.getString("EVENTDURATION");
//					String audioSource = rs.getString("AUDIOSOURCE");
//					String programRating = rs.getString("PROGRAMRATING");
//					String materialId = rs.getString("MATERIALID");
//					String description = rs.getString("DESCRIPTION");
//					String showNumber = rs.getString("SHOWNUMBER");
//					String secondaryOffset = rs.getString("SECONDARYOFFSET");
//					String showSegment = rs.getString("SHOWSEGMENT");
//					String breakType = rs.getString("BREAKTYPE");
//					String fileType = rs.getString("FILETYPE");
//					String fileLoc = rs.getString("FILELOC");
//					String fileName = rs.getString("FILENAME");
//					String houseNum = rs.getString("HOUSENUM");
//					String reconNum = rs.getString("RECONNUM");
//					String lineNum = rs.getString("LINENUM");
//					String acpVersion = rs.getString("ACP_VERSION");
//					String modifiedBy = rs.getString("MODIFIED_BY");
//					String modifiedDateTime = rs.getString("MODIFIED_DATE_TIME");
//					String action = rs.getString("ACTION");
//					String eventType = rs.getString("EVENTTYPE");
//					String logNumber = rs.getString("LOGNUMBER");
//					String airDate = rs.getString("AIRDATE");
//					String station = rs.getString("STATION");
//					String channel = rs.getString("CHANNEL");
//					String timeFormat = rs.getString("TIMEFORMAT");
//					String version = rs.getString("VERSION");
//					String weekday = rs.getString("WEEKDAY");
//					String julianDate = rs.getString("JULIANDATE");
//					String fill = rs.getString("FILL");
//					String timeMode = rs.getString("TIMEMODE");
//					String videoSource = rs.getString("VIDEOSOURCE");
//					String descriptionVideoMedia = rs.getString("DESCRIPTIVEVIDEOMEDIA");
//					String crtcCcdv = rs.getString("CRTC_CCDV");
//					String mediaAspectRatio = rs.getString("MEDIAASPECTRATIO");
//					String programSource = rs.getString("PROGRAMSOURCE");
//					String programType = rs.getString("PROGRAMTYPE");
//					String secondarySource = rs.getString("SECONDARYSOURCE");
//					String recordType = rs.getString("RECORDTYPE");
//					String firstEvent = rs.getString("FIRSTEVENT");
//					String lastSegment = rs.getString("LASTSEGMENT");
//					String realDurationFlag = rs.getString("REALDURATION_FLAG");
//					String breakComments = rs.getString("BREAKCOMMENTS");
//					String schedComment = rs.getString("SCHEDCOMMENT");
//					String dlComment = rs.getString("DLCOMMENT");
//					String describedVideo = rs.getString("DESCRIBEDVIDEO");
//					String contractNumber = rs.getString("CONTRACTNUMBER");
//					String advertiser = rs.getString("ADVERTISER");
//					String productName = rs.getString("PRODUCTNAME");
//					String sellingOptionName = rs.getString("SELLINGOPTIONNAMEM");
//					String fixInProgram = rs.getString("FIXINPROGRAM");
//					String fixInBreak = rs.getString("FIXINBREAK");
//					String onAir = rs.getString("ON_AIR");
//					String empty = rs.getString("EMPTY");
//					String dateTime = rs.getString("DATE_TIME");
//					String id = rs.getString("ID");
//					String s = rs.getString("S");
//					String title = rs.getString("TITLE");
//					String duration = rs.getString("DURATION");
//					String status = rs.getString("STATUS");
//					String device = rs.getString("DEVICE");
//					String ch = rs.getString("CH");
//					String reconcile = rs.getString("RECONCILE");
//					String type = rs.getString("TYPE");
//					String sec = rs.getString("SEC");
//
//					AppData appData = new AppData();
//
//					appData.setEventSequence(eventSequence);
//					appData.setScheduledTime(scheduledTime);
//					appData.setEventDuration(eventDuration);
//					appData.setAudioSource(audioSource);
//					appData.setProgramRating(programRating);
//					appData.setMaterialId(materialId);
//					appData.setDescription(description);
//					appData.setShowNumber(showNumber);
//					appData.setSecondaryOffset(secondaryOffset);
//					appData.setShowSegment(showSegment);
//					appData.setBreakType(breakType);
//					appData.setFileType(fileType);
//					appData.setFileLoc(fileLoc);
//					appData.setFileName(fileName);
//					appData.setHouseNum(houseNum);
//					appData.setReconNum(reconNum);
//					appData.setLineNum(lineNum);
//					appData.setAcpVersion(acpVersion);
//					appData.setModifiedBy(modifiedBy);
//					appData.setModifiedDateTime(modifiedDateTime);
//					appData.setAction(action);
//					appData.setEventType(eventType);
//					appData.setLogNumber(logNumber);
//					appData.setAirDate(airDate);
//					appData.setStation(station);
//					appData.setChannel(channel);
//					appData.setTimeFormat(timeFormat);
//					appData.setVersion(version);
//					appData.setWeekday(weekday);
//					appData.setJulianDate(julianDate);
//					appData.setFill(fill);
//					appData.setTimeMode(timeMode);
//					appData.setVideoSource(videoSource);
//					appData.setDescriptionVideoMedia(descriptionVideoMedia);
//					appData.setCrtcCcdv(crtcCcdv);
//					appData.setMediaAspectRatio(mediaAspectRatio);
//					appData.setProgramSource(programSource);
//					appData.setProgramType(programType);
//					appData.setSecondarySource(secondarySource);
//					appData.setRecordType(recordType);
//					appData.setFirstEvent(firstEvent);
//					appData.setLastSegment(lastSegment);
//					appData.setRealDurationFlag(realDurationFlag);
//					appData.setBreakComments(breakComments);
//					appData.setSchedComment(schedComment);
//					appData.setDlComment(dlComment);
//					appData.setDescribedVideo(describedVideo);
//					appData.setContractNumber(contractNumber);
//					appData.setAdvertiser(advertiser);
//					appData.setProductName(productName);
//					appData.setSellingOptionName(sellingOptionName);
//					appData.setFixInProgram(fixInProgram);
//					appData.setFixInBreak(fixInBreak);
//					appData.setOnAir(onAir);
//					appData.setEmpty(empty);
//					appData.setDateTime(dateTime);
//					appData.setId(id);
//					appData.setS(s);
//					appData.setTitle(title);
//					appData.setDuration(duration);
//					appData.setStatus(status);
//					appData.setDevice(device);
//					appData.setCh(ch);
//					appData.setReconcile(reconcile);
//					appData.setType(type);
//					appData.setSec(sec);
//					appDataList.add(appData);
//				}

				} catch (Exception e) {
				System.out.println(e.getMessage());
//					e.printStackTrace();

				}
				 finally {

				}
		}
		if (conn != null) {
			conn.close();
		}
		return errorList;
	}

}
