package com.kyc.fraud.controller;

import com.kyc.fraud.model.KycResponse;
import com.kyc.fraud.service.FraudCheckService;
import com.kyc.fraud.service.S3Service;
import com.kyc.fraud.service.TextractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/kyc")
@RequiredArgsConstructor
public class KycController {

    private final S3Service s3Service;
    private final TextractService textractService;
    private final FraudCheckService fraudCheckService;

    @PostMapping("/verify")
    public ResponseEntity<KycResponse> verify(@RequestParam MultipartFile file) throws Exception {
        String key = s3Service.uploadFile(file);
        var text = textractService.extractText(key);
        return ResponseEntity.ok(fraudCheckService.validate(text));
    }
}
