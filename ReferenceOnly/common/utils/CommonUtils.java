package com.egovja.tatransform.licencingmanagement.common.utils;

import com.codahale.metrics.annotation.Timed;
import io.astefanutti.metrics.aspectj.Metrics;
import org.apache.commons.lang3.StringUtils;

@Metrics(registry = "CommonUtils")
public final class CommonUtils {

    private CommonUtils() {
    }

    @Timed(name = "formatTRN")
    public static String formatTRN(Integer trn) {
        if(trn == null) return "";
        return StringUtils.leftPad(trn.toString(), 9, "0");
    }

    @Timed(name = "formatTRN")
    public static String formatTRN(String trn) {
        if(trn == null) return "";
        return StringUtils.leftPad(trn, 9, "0");
    }
}
