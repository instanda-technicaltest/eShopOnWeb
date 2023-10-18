package helper;

public class TCDetails {

	private String tcCount;
	private String tcName;
	private String tcResult;
	private String resultPath;

	public TCDetails(String tcCount, String tcName, String tcResult, String resultPath) {
		this.setTcCount(tcCount);
		this.setTcName(tcName);
		this.setTcResult(tcResult);
		this.setResultPath(resultPath);
		// TODO Auto-generated constructor stub
	}

	public String getTcResult() {
		return tcResult;
	}

	public void setTcResult(String tcResult) {
		this.tcResult = tcResult;
	}

	public String getTcCount() {
		return tcCount;
	}

	public void setTcCount(String tcCount) {
		this.tcCount = tcCount;
	}

	public String getTcName() {
		return tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public String getResultPath() {
		return resultPath;
	}

	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}
	

	
	
}
