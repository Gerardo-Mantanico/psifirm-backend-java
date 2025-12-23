package com.pifirm.domain.service;


import com.pifirm.domain.exception.GeneralException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class  S3Service {

    private final S3Client s3Client;
    private final String bucketName;

    public S3Service(@Value("${aws.s3.bucketName}") String bucketName,
                     @Value("${aws.accessKeyId}") String accessKey,
                     @Value("${aws.secretKey}") String secretKey,
                     @Value("${aws.s3.region}") String region) {

        this.bucketName = bucketName;

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    public String uploadFile(MultipartFile file, String fileName) {
        try {
            // Convertir MultipartFile a byte array
            byte[] fileBytes = file.getBytes();

            // Crear la solicitud de subida
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();

            // Subir el archivo a S3
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileBytes));

            // Generar la URL pública del archivo
            return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toString();

        } catch (IOException e) {
            throw new RuntimeException("Error al procesar el archivo", e);
        }
    }



    //funcion para subir multiples imagenes
    public List<String> listImgs(List<MultipartFile> imgs) {
        List<String> imageUrls = new ArrayList<>();

        for (int i = 0; i < imgs.size(); i++) {
            MultipartFile img = imgs.get(i);

            // Validar el tipo de archivo
            if (!img.getContentType().startsWith("image/")) {
                throw new IllegalArgumentException("Solo se permiten archivos de imagen");
            }

            // Generar un nombre único para el archivo
            String fileName = generateFileName("image", i, img.getOriginalFilename());

            System.out.println("nombre del archivo " + fileName);
            // Subir a S3 y obtener la URL
            String imageUrl = this.uploadFile(img, fileName);
            imageUrls.add(imageUrl);
            //  imageUrls.add(fileName);
        }
        return imageUrls;
    }


    private String generateFileName(String productName, int index, String originalFileName) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String extension = originalFileName != null ?
                originalFileName.substring(originalFileName.lastIndexOf(".")) : ".jpg";
        // Limpiar el nombre del producto para usarlo en el nombre del archivo
        String cleanProductName = productName.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
        return String.format("%s-%s-%d%s", cleanProductName, timeStamp, index, extension);
    }


    //funcion para subir un archivo pdf
    public String uploadFilePdf(MultipartFile file) {
        try {
            // Validar el tipo de archivo CORREGIDO
            if (!file.getContentType().equals("application/pdf")) {
                throw new GeneralException("invalid-file-type", "Solo se permiten archivos PDF");
            }
            // Generar un nombre único para el archivo
            String fileName = generateFileName("comercio", 0, file.getOriginalFilename());
            System.out.println("nombre del archivo " + fileName);
            // Subir a S3 y obtener la URL
            return this.uploadFile(file, fileName);
        }  catch (GeneralException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error técnico al subir el archivo", e);
        }
    }


    //funcion para subir una imagen
    public String uploadImg(MultipartFile img, String name) {
        try {
            // Validar el tipo de archivo
            if (!img.getContentType().startsWith("image/")) {
                throw new GeneralException("invalid-image-type", "Solo se permiten archivos de imagen");
            }

            // Generar un nombre único para el archivo
            String fileName = generateFileName("logo-"+name, 0, img.getOriginalFilename());

            System.out.println("nombre del archivo " + fileName);
            // Subir a S3 y obtener la URL
            return  this.uploadFile(img, fileName);
        }  catch (GeneralException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error técnico al subir el archivo", e);
        }
    }
}