# Hi!

Thanks for checking this out, if you have any questions don't hesitate to give me a shout.

## Setup

- ensure the target site is running (Take note of the URL)
- cd into the `tests` directory
- run `npm install`
- configure the `.env` file with the URL from step 1
- run the tests using `npm run tests`
- for a HTML report use `npm run report`

## Tech Stack

This project includes:

- Node
- Playwright
- Typescript

_Note: I've committed the .env files for your convenience to run the tests_
_Note: The purchase E2E test is sometimes flaky on webkit browsers - I've managed to reproduce this manually intermittently, so is likely a real bug._
