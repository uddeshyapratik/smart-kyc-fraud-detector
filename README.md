# 🛡️ Smart KYC Fraud Detector

Smart KYC Fraud Detector is a backend application that automates **Know Your Customer (KYC)** document verification using AI-powered text extraction and business rule validation.

This project demonstrates how banks and fintech companies automate onboarding and detect suspicious submissions using cloud-based AI services.

---

## Step 1️⃣: Understand the Problem

Manual KYC verification is:
- Time-consuming
- Error-prone
- Costly at scale

Banks must ensure that:
- Mandatory customer details are present
- Documents are valid and compliant
- Suspicious submissions are detected early

---

## Step 2️⃣: Solution Overview

This application:
- Accepts identity documents via REST API
- Uses AI to extract text from documents
- Applies business rules on extracted data
- Flags suspicious or incomplete submissions

---

## Step 3️⃣: High-Level Architecture


---

## Step 4️⃣: Tech Stack Used

- Java 17
- Spring Boot
- AWS S3
- AWS Textract
- Maven
- REST APIs

---

## Step 5️⃣: Application Flow (Step-by-Step)

1. User uploads a KYC document (PAN / Aadhaar / Passport)
2. The document is securely stored in Amazon S3
3. AWS Textract extracts structured text from the document
4. The application parses extracted text
5. Business rules validate mandatory KYC fields:
   - Name
   - Date of Birth (DOB)
   - PAN format
6. The system flags suspicious or non-compliant submissions
7. Verification result is returned via REST API

---

## Step 6️⃣: Business Validation Rules

After text extraction, the following rules are applied:

| Rule | Description |
|----|------------|
| Name validation | Name field must be present |
| DOB validation | Date of birth must be present |
| PAN validation | Must match regex `[A-Z]{5}[0-9]{4}[A-Z]` |

If any rule fails, the document is marked as **SUSPICIOUS**.

---

## Step 7️⃣: API Details

### Base URL
http://localhost:8080


#### Request
- Content-Type: `multipart/form-data`
- Body parameter:
   - `file` (KYC document)

#### Sample cURL
```bash
curl -X POST http://localhost:8080/kyc/verify \
  -F "file=@pan_card.jpg"


