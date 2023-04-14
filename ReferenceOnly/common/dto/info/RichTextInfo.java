/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.RichText;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This info is for RichText DTO that contains description in plainFormate and
 * FormattedFormate.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public class RichTextInfo implements RichText, Serializable {

    @ApiModelProperty(notes = "The plain description for the model",
            allowEmptyValue = true,
            example = "plan text",
            dataType = "String",
            required = false)
    private String plain;

    @ApiModelProperty(notes = "The formatted description for the model",
            allowEmptyValue = true,
            example = "<b>formatted text</b>",
            dataType = "String",
            required = false)
    private String formatted;

    public RichTextInfo() {
    }

    /**
     * This is copy ConStructure that make deep copy of the richText.
     *
     * @param richText richText
     */
    public RichTextInfo(RichText richText) {
        if (richText != null) {
            this.plain = richText.getPlain();
            this.formatted = richText.getFormatted();
        }
    }

    @Override
    public String getPlain() {
        return plain;
    }

    public void setPlain(String plainText) {
        this.plain = plainText;
    }

    @Override
    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formattedText) {
        this.formatted = formattedText;
    }

    @Override
    public String toString() {
        return "RichTextInfo{" + "plain=" + plain + ", formatted=" + formatted + '}';
    }
}
