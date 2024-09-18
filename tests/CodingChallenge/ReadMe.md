# Project OverView 

Test Automation framework to provide fast feedback that the app continues to provide value to our customers. 
The framework automates testing for an E-commerce site **E-Shop**, using POM pattern and allow flexibility of Browser Configuration and ViewportSize.


## Tools used
- **Specflow**: BDD framework 
- **Playwright**: UI automation
- **.Net**: Object Oriented Program used
- **HttpClient**: Api tests


## Running the Tests


 Pre-req: Download the **specflow Plugin** for Visual studio or  **Reqnroll** JetBrain Rider  
- Clone the Repo
- running 'dotnet restore' should restore all the packages 
```
dotnet restore
``` 
- You'll need to install the browsers, run the following powershell command at the **ROOT** directory of the project.
```
pwsh bin/Debug/net6.0/playwright.ps1 install
```

- Update appsetting.json to custom runsetting.

**main areas of interest:**
- Browser : so select which browser to run 
- BrowserTypeLaunchOptions : To see the execution of the brought , set this to false
set Headless 
``` json
{
  "Browser": "firefox",
  "BaseUrl": "https://localhost:5001/",
  "ApiUrl": "https://localhost:5099/",
  "BrowserContextOptions": {
    "IgnoreHTTPSErrors": true,
    "Locale": "en-US",
    "ViewportSize": {
      "Width": 1280,
      "Height": 720
    }
  },
  "BrowserTypeLaunchOptions": {
    "Headless": false
  }
}
```
You can then build and run the solution via the test explorer on visual Studio or using the CLI command below 

```
dotnet build
dotnet test
```
### Other Commands
Generate the LivingDoc and test results report after running your tests using the below commands:
```
dotnet test --logger:html
specflow-plus-livingdoc
livingdoc feature-folder . --output TestExecution.html
```
# Types of Tests 
UI tests

- Valid and Invalid Login scenarios
- E2E of a user selecting items to add in the basket and successfully checking out after a login
- Authenticated checkout: User is PRE-authenticated (leveraging playwright reusable state feature) and successfully checkout
- Accessibility test: Test homepage has no serious accessibility rules violation

API test

- Valid and Invalid request to the Authentication API

