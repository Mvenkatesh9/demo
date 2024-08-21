package com.clinivapps.model;

import java.util.*;

public class SMS
{
    private String phoneNumber;
    private Map<String, String> valueMap;
    private String template;
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getTemplate() {
        return this.template;
    }
    
    public void setTemplate(final String template) {
        this.template = template;
    }
    
    public Map<String, String> getValueMap() {
        return this.valueMap;
    }
    
    public void setValueMap(final Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }
}