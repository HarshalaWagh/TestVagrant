# TestVagrant - Movie Details Automation Framework

## Overview

Selenium WebDriver test automation framework built with Java, Maven, and TestNG. Validates movie information across IMDb and Wikipedia and compares data consistency between platforms.

## Technology Stack

| Technology | Version |
|------------|---------|
| Java | 11+ |
| Maven | 3.6+ |
| Selenium WebDriver | 4.15.0 |
| TestNG | 7.8.0 |
| WebDriver Manager | 5.6.2 |
| Allure | 2.25.0 |

---

## Quick Start

```bash
# Clone repository
git clone https://github.com/YOUR_USERNAME/TestVagrant.git
cd TestVagrant

# Install dependencies
mvn clean install -DskipTests

# Run tests
mvn test

# Generate Allure report
mvn allure:serve
```

---

## Running Tests

### Run Locally

**Run All Tests:**
```bash
mvn clean test
```

**Run Specific Test:**
```bash
mvn test -Dtest=WikipediaTest
mvn test -Dtest=IMDbTest
mvn test -Dtest=CompareData
```

**Run from IDE:**
- Right-click on test class → Run as TestNG Test

### Run on GitHub Actions

Tests automatically run on push/pull request to main, master, or develop branches.

**Manual Trigger:**
1. Go to **Actions** tab on GitHub
2. Select workflow
3. Click **Run workflow**

**View Results:**
1. Go to **Actions** tab
2. Click on workflow run
3. View logs and download artifacts:
   - Allure reports
   - Test reports
   - Screenshots

---

## Project Structure

```
TestVagrant/
├── src/main/java/
│   ├── pojo/          - Browser setup classes
│   ├── pom/           - Page Object Model classes
│   └── utility/       - Helper utilities (Screenshot, Listener)
├── src/test/java/
│   └── TestCases/     - Test classes
├── target/            - Test results and reports
├── pom.xml            - Maven configuration
└── testng.xml         - TestNG suite configuration
```
