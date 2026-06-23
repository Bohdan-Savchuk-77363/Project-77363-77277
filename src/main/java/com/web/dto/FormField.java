package com.web.dto;

public class FormField {
    private String name;
    private String placeholder;
    private boolean required;
    private String type;

    public FormField (String name, String placeholder, boolean required, String type){
        this.name = name;
        this.placeholder = placeholder;
        this.required = required;
        this.type = type;
    }
    public String getType(){return type;}
    public String getName(){return name;}
    public String getPlaceholder(){return placeholder;}
    public boolean isRequired(){return required;}
}
