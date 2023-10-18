package helper;

public class FailedTCDetails {

	private String tcCount;
	private String tcName;
	private String tcFailureReason;
	private String screenPath;

	public FailedTCDetails(String tcCount, String tcName, String tcFailureReason, String screenPath) {
		this.setTcCount(tcCount);
		this.setTcName(tcName);
		this.setTcFailureReason(tcFailureReason);
		this.setScreenPath(screenPath);
		// TODO Auto-generated constructor stub
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



	public String getTcFailureReason() {
		return tcFailureReason;
	}



	public void setTcFailureReason(String tcFailureReason) {
		this.tcFailureReason = tcFailureReason;
	}



	public String getScreenPath() {
		return screenPath;
	}



	public void setScreenPath(String screenPath) {
		this.screenPath = screenPath;
	}


	

	
	
}
