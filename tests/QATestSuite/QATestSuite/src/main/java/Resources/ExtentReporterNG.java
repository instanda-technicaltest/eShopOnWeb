package Resources;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports extent;

    public static ExtentReports getReportObject()
    {
        String path =System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Mousumi Ghosh");
        return extent;

    }
}
