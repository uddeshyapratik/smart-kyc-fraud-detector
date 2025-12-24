package com.kyc.fraud.service;

import com.kyc.fraud.model.KycResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FraudCheckService {

    public KycResponse validate(List<String> lines) {
        boolean name=false, dob=false, pan=false;

        for (String l : lines) {
            if (l.toLowerCase().contains("name")) name = true;
            if (l.toLowerCase().contains("dob")) dob = true;
            if (l.matches(".*[A-Z]{5}[0-9]{4}[A-Z].*")) pan = true;
        }

        List<String> issues = new ArrayList<>();
        if (!name) issues.add("Name missing");
        if (!dob) issues.add("DOB missing");
        if (!pan) issues.add("Invalid PAN");

        return new KycResponse(issues.isEmpty() ? "VERIFIED" : "SUSPICIOUS", issues, lines);
    }
}
