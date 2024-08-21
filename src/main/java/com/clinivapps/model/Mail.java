package com.clinivapps.model;

import java.util.*;

public class Mail
{
    private String mailTo;
    private String[] multipleMailTo;
    private String mailCc;
    private String mailBcc;
    private String templateName;
    private Map<String, String> valueMap;
    
    public String[] getMultipleMailTo() {
        return this.multipleMailTo;
    }
    
    public void setMultipleMailTo(final String[] multipleMailTo) {
        this.multipleMailTo = multipleMailTo;
    }
    
    public String getMailTo() {
        return this.mailTo;
    }
    
    public void setMailTo(final String mailTo) {
        this.mailTo = mailTo;
    }
    
    public String getMailCc() {
        return this.mailCc;
    }
    
    public void setMailCc(final String mailCc) {
        this.mailCc = mailCc;
    }
    
    public String getMailBcc() {
        return this.mailBcc;
    }
    
    public void setMailBcc(final String mailBcc) {
        this.mailBcc = mailBcc;
    }
    
    public String getTemplateName() {
        return this.templateName;
    }
    
    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }
    
    public Map<String, String> getValueMap() {
        return this.valueMap;
    }
    
    public void setValueMap(final Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }
}