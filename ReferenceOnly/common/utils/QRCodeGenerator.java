
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.utils;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.licencingmanagement.common.constant.QRConstant;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.astefanutti.metrics.aspectj.Metrics;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author akshar
 */
@Metrics(registry = "QRCodeGenerator")
public final class QRCodeGenerator {

    private QRCodeGenerator() {
    }

    //    private static final int Width = 200;
//    private static final int Height = 200;
    @Timed(name = "generateQRCodeImage")
    public static byte[] generateQRCodeImage(Object obj, int width, int height)
            throws WriterException, IOException {
        StringBuilder sb = new StringBuilder();
        ReflectionUtils.doWithFields(obj.getClass(), field -> {
            field.setAccessible(true);
            if (!StringUtils.isEmpty(field.get(obj))) {
                sb.append(field.getName()).append(" : ");
                sb.append(field.get(obj)).append("\n");
            }
        });
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(sb.toString(), BarcodeFormat.QR_CODE, width, height,
                com.google.common.collect.ImmutableMap.of(com.google.zxing.EncodeHintType.MARGIN, 0));

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    @Timed(name = "generateQRCodeImageFromMap")
    public static byte[] generateQRCodeImageFromMap(Map<String, String> map, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        StringBuilder builder = new StringBuilder();
        map.entrySet().forEach(entry -> {
            if (entry.getKey().endsWith(QRConstant.DETAILS)) {
                builder.append("\n");
            }
            builder.append(entry.getKey());
            if (!StringUtils.isEmpty(entry.getValue())) {
                builder.append(": ")
                        .append(entry.getValue());
            }
            builder.append("\n");
        });
        BitMatrix bitMatrix = qrCodeWriter.encode(builder.toString(), BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();

//        StringBuilder sb = new StringBuilder();
//        ReflectionUtils.doWithFields(map.getClass(), field -> {
//            field.setAccessible(true);
//            if (!StringUtils.isEmpty(map)) {
//                sb.append(field.getName()).append(" : ");
//                sb.append(field.get(map)).append("\n");
//            }
//        });
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(sb.toString(), BarcodeFormat.QR_CODE, width, height, 
//                com.google.common.collect.ImmutableMap.of(com.google.zxing.EncodeHintType.MARGIN, 0));
//
//        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
//        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
//        byte[] pngData = pngOutputStream.toByteArray();
//        return pngData;
    }

    @Timed(name = "generateQRCodeFromString")
    public static byte[] generateQRCodeFromString(String str, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(str, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }
}
