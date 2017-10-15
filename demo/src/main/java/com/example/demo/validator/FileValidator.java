package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.form.Myform;

@Component
public class FileValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return Myform.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	Myform bucket = (Myform) target;

        if (bucket.getFile() != null && bucket.getFile().isEmpty()){
            errors.rejectValue("file", "file.empty");
        }
    }
}