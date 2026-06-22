package com.web.service;

import com.web.dto.FormField;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class FormsUiService {
    public static List<FormField> getRegistrationFields(){
        return List.of(
                new FormField("name","Name", true, "text"),
                new FormField("email","Email", true, "email"),
                new FormField("password","Password", true, "password"));
    }
    public static List<FormField> getLoginFields(){
        return List.of(
                new FormField("email","Email", true, "email"),
//                new FormField("photo", "Upload Photo", false, "file"),
                new FormField("password","Password", true, "password"));
    }

    public static List<FormField> getProfileFields(){
        return List.of(
                new FormField("age", "Enter your age",true,"int"),
                new FormField("country", "Country",true,"text"),
                new FormField("photo", "Photo",true,"file")

        );
    }
}
