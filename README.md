# Parabank Automation Framework

![CI](https://github.com/SireeshaKolluru10/parabank-automation/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.18.1-green)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red)
![REST Assured](https://img.shields.io/badge/REST%20Assured-5.4.0-blue)
![Maven](https://img.shields.io/badge/Maven-3.x-purple)

---

## About the Project

A **Hybrid Test Automation Framework** built from 
scratch for the Parabank banking application. 
Covers UI automation, REST API testing, and 
cross-layer validation using industry standard 
tools and design patterns.

**Application under test:**
https://parabank.parasoft.com

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| UI Automation | Selenium WebDriver 4.18.1 |
| API Testing | REST Assured 5.4.0 |
| Test Framework | TestNG 7.9.0 |
| Build Tool | Maven |
| Reporting | Extent Reports 5.1.1 |
| Test Data | Java Faker |
| CI/CD | GitHub Actions |
| Driver Management | WebDriverManager |

---

## Framework Architecture

```
Hybrid Framework — POM + Data Driven
├── Base Layer
│   ├── BaseTest    — driver setup and teardown
│   └── BasePage    — reusable page interactions
├── Pages Layer
│   ├── Page classes for each application page
│   └── All extend BasePage
├── Tests Layer
│   ├── Test classes organised by feature
│   └── All extend BaseTest
└── Utilities Layer
    ├── ConfigReader    — config management
    ├── FakerUtil       — dynamic test data
    ├── APIUtil         — REST Assured wrapper
    ├── ExtentReportManager — HTML reporting
    ├── ScreenshotUtil  — failure screenshots
    └── RetryAnalyser   — flaky test handler
```

## Project Structure

```
parabank-automation/
├── src/main/java/
│   ├── com.parabank.base/
│   ├── com.parabank.listeners/
│   ├── com.parabank.pages/
│   └── com.parabank.utilities/
├── src/test/java/
│   └── com.parabank.tests/
├── src/test/resources/
│   ├── config.properties
│   └── testdata/
├── logs/
├── reports/
├── screenshots/
├── .github/workflows/ci.yml
├── BugReport.md
└── testng.xml
```

## Test Scenarios Covered

### UI Tests — 17 scenarios

| Module | Scenarios |
|---|---|
| Login | Valid login, invalid credentials, empty fields |
| Registration | Valid registration, empty form, duplicate username, password mismatch |
| Account | Open checking account, open savings account |
| Transfer Funds | Valid transfer, empty amount |
| Bill Pay | Valid bill payment, empty form validation |
| Transactions | Find by amount, invalid amount search |
| Cross Layer | Register via UI verify via API, Transfer via UI verify via API |

### API Tests — 5 scenarios

| Scenario | Assertion |
|---|---|
| Login API | Status 200, customer ID, name verified, response time |
| Get accounts | Status 200, account count > 0 |
| Invalid login | Status 400 |
| Get account details | Account type and balance not null |
| Customer accounts count | At least one account exists |

### Total — 22 test scenarios

## Prerequisites
---
Java 17+
Maven 3.x
Chrome browser
Eclipse or IntelliJ IDE

---

## How to Run

**Clone the repository:**
```bash
git clone https://github.com/SireeshaKolluru10/parabank-automation.git
cd parabank-automation
```

**Run full test suite:**
```bash
mvn test
```

**Run in headless mode:**
```bash
mvn test -Dheadless=true
```

**Run specific test class:**
```bash
mvn test -Dtest=LoginTest
```

---

## Test Reports

After test execution find reports here:

reports/AutomationReport.html  — Extent HTML report
screenshots/                   — Failure screenshots
Open `AutomationReport.html` in any browser to view 
detailed results with pass/fail status and screenshots.

---

## CI/CD Pipeline

GitHub Actions pipeline triggers automatically on 
every push to main branch.

**Pipeline steps:**
1. Checkout code
2. Setup Java 17
3. Cache Maven dependencies
4. Install Chrome
5. Run tests in headless mode
6. Upload Extent Report as artifact
7. Upload screenshots on failure

View pipeline runs:
https://github.com/SireeshaKolluru10/parabank-automation/actions

---

## Known Defects

See [BugReport.md](BugReport.md) for documented defects.

| Bug ID | Title | Status |
|---|---|---|
| BUG-001 | Zero amount transfer allowed | Open — test disabled |
| BUG-002 | AJAX timing on shared server | Known limitation |

---

## Author

**Sireesha Kolluru**
QA Automation Engineer
[LinkedIn](https://www.linkedin.com/in/sireesha-kolluru-554988181/)
[GitHub](https://github.com/SireeshaKolluru10)

---

## Key Highlights

- Built from scratch — no tutorial code
- 22 automated test scenarios across 8 modules
- Dynamic test data using Java Faker
- Auto screenshot capture on test failure
- Retry mechanism for flaky tests
- GitHub Actions CI pipeline
- Professional HTML reports with dark theme
- Cross layer validation — UI action verified via API
- Real defect found and documented — BugReport.md
- Response time assertions on API tests





