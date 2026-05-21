# Bug Report — Parabank Automation Project

## BUG-001
**Title:** Zero amount fund transfer is allowed

| Field 	| Details |	
| Bug ID 	| BUG-001 |
| Severity  | High |
| Priority  | High |
| Status    | Open |
| Reported By | Sireesha Kolluru |
| Date 		| 21-May-2026 |
| Environment| https://parabank.parasoft.com |
| Build 	 | Latest |

---
### Description
The application allows fund transfer with zero
amount and displays a success message.
Zero amount transfers should be rejected with
a validation error message.

---

### Steps to Reproduce
1. Login with valid credentials — john/demo
2. Click Transfer Funds from left navigation
3. Enter 0.00 in the Amount field
4. Select any From Account
5. Select any To Account
6. Click Transfer button

---

### Expected Result
Application should display a validation error:
"Transfer amount must be greater than zero"
Transfer should NOT be processed.

---

### Actual Result
Application shows success message:
"Transfer Complete! $0.00 has been transferred
from account #12345 to account #12456"
Transfer is processed successfully with
zero amount.

---

### Impact
This defect could allow erroneous transactions
in a real banking system. Zero amount transfers
add unnecessary transaction records and could
be exploited for fraudulent activity.

---

### Test Case Reference
TransferFundsTest.java — zeroAmountTransferTest
Test is intentionally asserting false to flag
this defect. Test will pass once defect is fixed.

---

## BUG-002
**Title:** Open Account page — intermittent failure
due to AJAX timing

| Field | Details |
|---|---|
| Bug ID | BUG-002 |
| Severity | Low |
| Priority | Low |
| Status | Known limitation |
| Reported By | Sireesha Kolluru |
| Date | 21-May-2026 |
| Environment | https://parabank.parasoft.com |
| Build | Latest |

---

### Description
When running the full test suite against
Parabank public server, account creation tests
fail intermittently. Tests pass consistently
when run individually.

---

### Root Cause
Parabank is a shared public demo server used
by thousands of people simultaneously. Rapid
sequential test execution causes server
throttling and delayed AJAX responses.

---

### Impact
This is an environment limitation — not a
framework defect. In a dedicated test
environment this issue would not occur.

---

### Workaround
RetryAnalyser automatically retries failed
tests once. Tests pass on retry in most cases.
Run tests individually for consistent results.