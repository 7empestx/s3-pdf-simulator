package com.gstarkma.s3pdfsimulator;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@RestController
public class GetPDF {

    private static final String ACCESS_KEY = System.getenv("ACCESS_KEY");
    private static final String SECRET_KEY = System.getenv("SECRET_KEY");

    private static final String REGION_NAME = "us-west-2";
    private static final String BUCKET_NAME = "gstarkma-bucket";
    private static final String OBJECT_KEY = "file-example_PDF_1MB.pdf";

    @GetMapping("api/download")
    public ResponseEntity<byte[]> downloadFile() throws IOException {
        // Set up AWS credentials and S3 client
        BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.fromName(REGION_NAME))
                .build();

        try {
            // Download the PDF file from S3
            S3Object s3object = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, OBJECT_KEY));
            InputStream inputStream = s3object.getObjectContent();

            byte[] bytes = IOUtils.toByteArray(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData(s3object.getKey(), s3object.getKey());

            return ResponseEntity.ok().headers(headers).body(bytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
