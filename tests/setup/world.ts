import { AfterAll, Before, After, BeforeAll, setDefaultTimeout, ITestCaseHookParameter } from '@cucumber/cucumber';
import { Browser, chromium, Page } from 'playwright';
import * as fs from 'fs-extra';
import { commonContext } from '../setup/global'
import { createLogger } from "winston";
import { options } from "./logger";
import { getEnv } from '../setup/types/env';
import axios from 'axios'

// Load environment variables from .env file
getEnv();

// Declare 'page' and 'browser' variables globally for use across test scenarios
let page: Page;
let browser: Browser;
let authToken: string;
setDefaultTimeout(5000000);


BeforeAll(async () => {
    fs.emptyDir('./test-results/logs', err => {
        if (err) {
            console.log(err);
            return;
        }
        console.log('All files deleted from directory Successfully.');
    })
    await new Promise(resolve => setTimeout(resolve, 1000));
    getEnv();

    // Check if the test is for API Testing (based on ENV variable)
    if (process.env.ENV === 'APITesting') {
        console.log("API Testing Framework");
        // Make the request to authenticate and get the token
        const response = await axios.post(process.env.API_BASE_URL + '/authenticate', {
            username: process.env.USERNAME,
            password: process.env.PASSWORD
        });

        if (response.status === 200) {
            authToken = response.data.token; // Store token for use in other requests
            console.log('Authentication successful, token stored');
        } else {
            throw new Error('Failed to authenticate');
        }
    }
    else {
        try {
            browser = await chromium.launch({ headless: false });
            const context = await browser.newContext();
            page = await context.newPage();
            await page.goto(process.env.BASE_URL!);
            console.log('Captured site title as :' + await page.title())

        }
        catch (error) {
            console.log('Chromium navigation to helix site failed due to' + error);
            throw new Error('Chromium navigation to helix site failed due to ' + error);
        }
        return page;
    }

});

AfterAll(async () => {
    if (process.env.ENV === 'APITesting') {
        commonContext.logger.close();
    }
    else {
        await page.close();
        await browser.close();
        commonContext.logger.close();
    }
});

Before(async function (scenario: ITestCaseHookParameter) {
    const scenarioName = scenario.pickle.name + ' ' + scenario.pickle.id;

    // Create a logger instance for each test scenario
    commonContext.logger = createLogger(options(scenarioName));
    commonContext.logger.info("Scenario: " + scenarioName);
});



After(async function (scenario: ITestCaseHookParameter) {
    const scenarioName = scenario.pickle.name + ' ' + scenario.pickle.id;

    // Define the path to the log file for the current scenario
    const logFilePath = './test-results/logs/' + scenarioName + '.log';

    // Add a slight delay before reading the log file (ensures file write is completed)
    await new Promise(resolve => setTimeout(resolve, 1000));

    const logFileContent = fs.readFileSync(logFilePath, 'utf-8');

    // Attach the log file content to the Cucumber test report
    this.attach(logFileContent, 'text/plain');
});

export { page, browser, axios, authToken };
