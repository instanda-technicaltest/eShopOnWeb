package helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class OverallUnitTestReporter {
	
	public static String templatePath = System.getProperty("user.dir")+"\\src\\main\\resources\\Report\\OverallUnitTestTemplate.html";
//	public static List<TCDetails> allTCdetails = new ArrayList<TCDetails>();
//	public static List<FailedTCDetails> failedTCdetails = new ArrayList<FailedTCDetails>();
//	public static String failureFlag="";
//	public static int failureCount = 1;
//	public static int TCCount = 0;
//	public static int PassedTCCount = 0;
//	public static int FailedTCCount = 0;
//	public static int SkippedTCCount = 0;
//	public static String TCName="";
//	public static String TCStatus="";
//	public static String reasonForFailure="";
//	public static String failureScreen="";
//	public static HashMap<String, String> failureMap = new HashMap<String, String>();
//	private static String allTCDetailsInreport="";
//	private static String failedTCDetailsInreport="";


	private static String TCCountPlaceholder = "<!--TotalTCCount-->"	;
	private static String PassedTCCountPlaceholder = "<!--TotalPassCount-->"	;
	private static String FailesTCCountPlaceholder = "<!--TotalFailCount-->"	;
	private static String SkippedTCCountPlaceholder = "<!--TotalSkipCount-->"	;
	private static String allTCDetailsPlaceholder = "<!--Insert_AllTCDetails-->"	;
	private static String failedTCDetailsPlaceholder = "<!--Insert_FailedTCDetails-->"	;

	
	public static String fileName = "Overall Unit Testing Report.html";
	public static int counter = 0;
	public static File filePath;
	public static String reportPath="";
	public static File file ;
	public static String reportIn="";
	public static String featureName = "";

	public static String tableTemp = "<tr>\r\n"
			+ "<th colspan=\"4\"> Scenario Name  : Scenario_Name_Holder </th>\r\n"
			+ "</tr>\r\n"
			+ "<tr>\r\n"
			+ "<th width=\"10%\">Step</th>\r\n"
			+ "<th width=\"40%\">Expected Result</th>\r\n"
			+ "<th width=\"40%\">Actual Result</th>\r\n"
			+ "<th width=\"10%\">Result</th>\r\n"
			+ "</tr>";

	//In Testng afterClass
	public static void tcDetailsReport(String tcNo, String tcName,String result, String resultPath) {

		TCDetails r = new TCDetails(tcNo,tcName,result,resultPath);
		OverallReporter.allTCdetails.add(r);

	}

	//In Testng afterClass if TC failed
	public static void failedTCDetails(String failedTCCount, String tcName,String reasonForFailure, String screenPath) {

		FailedTCDetails r = new FailedTCDetails(failedTCCount,tcName,reasonForFailure,screenPath);
		OverallReporter.failedTCdetails.add(r);

	}

	public static void createOverallReportFile () {
		try {
			reportIn = new String(Files.readAllBytes(Paths.get(templatePath)));

			filePath = new File(OverallReporter.path);
			if (!filePath.mkdirs())
			{
				System.err.println("Could not create parent directories ");
			}
			file = new File(filePath, fileName);
			file.createNewFile();
			reportPath=filePath+"\\"+fileName;
		}catch(Exception e) {

			System.out.println("Error when writing report file:\n" + e.toString());
		}

	}



	//In testng aftersuite
	public static void addTCDetailsToReport() {
		try {
			for (TCDetails i : OverallReporter.allTCdetails) {
				if(!i.getTcResult().equals("Fail")) {
					OverallReporter.allTCDetailsInreport = OverallReporter.allTCDetailsInreport 
						+ "<tr>"
						+ "<td>"+ i.getTcCount()+"</td>"
						+ "<td><a href=\""+i.getResultPath()+"\" target=\"_blank\">"+ i.getTcName() + "</a></td>"
						+ "<td>"+ i.getTcResult()+"</td>"
						+ "</tr>";			
			}else {
				
				OverallReporter.allTCDetailsInreport = OverallReporter.allTCDetailsInreport 
						+ "<tr>"
						+ "<td>"+ i.getTcCount()+"</td>"
						+ "<td><a href=\""+i.getResultPath()+"\" target=\"_blank\">"+ i.getTcName() + "</a></td>"
						+ "<td>"+ "<font color=\"red\">Fail</font>"+"</td>"
						+ "</tr>";			
			}
			}


			reportIn = reportIn.replace(TCCountPlaceholder, OverallReporter.TCCount+"");
			reportIn = reportIn.replace(PassedTCCountPlaceholder, OverallReporter.PassedTCCount+"");
			reportIn = reportIn.replace(FailesTCCountPlaceholder, OverallReporter.FailedTCCount+"");
			reportIn = reportIn.replace(SkippedTCCountPlaceholder, OverallReporter.SkippedTCCount+"");
			reportIn = reportIn.replace(allTCDetailsPlaceholder, OverallReporter.allTCDetailsInreport+"");
			reportIn = reportIn.replace(failedTCDetailsPlaceholder, OverallReporter.failedTCDetailsInreport+"");

		}catch(Exception e) {

			System.out.println("Error when writing report file:\n" + e.toString());
		}
	}

	//In testng aftersuite
	public static void writeResults() {
		try {
			System.out.println("writing to"+ file.getAbsolutePath());
			Files.write(Paths.get(reportPath),reportIn.getBytes(),StandardOpenOption.CREATE);
			reportIn="";

		} catch (Exception e) {

			System.out.println("Error when writing report file:\n" + e.toString());
		}
	}

	public static void addFailureRows() {
		// TODO Auto-generated method stub
		if(OverallReporter.failureMap.size()!=0) {
		String failureStep ="";
		int span = OverallReporter.failureMap.size();
		failureStep=failureStep
				+ "<tr>"
				+ "<td rowspan=\""+span+"\">"+ OverallReporter.FailedTCCount+"</td>"
				
				+ "<td rowspan=\""+span+"\">"+ OverallReporter.TCName+"</td>";
		TreeSet<String> myTreeSet = new TreeSet<String>();
        myTreeSet.addAll(OverallReporter.failureMap.keySet());
		for (String key : myTreeSet) {
			failureStep=failureStep+ "<td><a href=\""+OverallReporter.failureMap.get(key)+"\" target=\"_blank\">"+ key + "</a></td>"
					+ "</tr><tr>";
		}
		
		OverallReporter.failedTCDetailsInreport = OverallReporter.failedTCDetailsInreport+failureStep;
		}
	}
	


}
