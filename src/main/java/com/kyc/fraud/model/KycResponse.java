package com.kyc.fraud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class KycResponse {
    private String status;
    private List<String> issues;
    private List<String> extractedText;
}
