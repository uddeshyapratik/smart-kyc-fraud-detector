package com.kyc.fraud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class TextractService {

    private final TextractClient textractClient;

    @Value("${aws.bucketName}")
    private String bucketName;

    public TextractService(TextractClient textractClient) {
        this.textractClient = textractClient;
    }

    public List<String> extractText(String key) {
        AnalyzeDocumentRequest request = AnalyzeDocumentRequest.builder()
                .document(Document.builder()
                        .s3Object(S3Object.builder().bucket(bucketName).name(key).build())
                        .build())
                .featureTypes(FeatureType.FORMS)
                .build();

        AnalyzeDocumentResponse response = textractClient.analyzeDocument(request);

        List<String> lines = new ArrayList<>();
        for (Block block : response.blocks()) {
            if (block.blockType() == BlockType.LINE) {
                lines.add(block.text());
            }
        }
        return lines;
    }
}
