package com.memos.backend.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ErrorHandler implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    ApiError handleError(WebRequest webRequest) {
        Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of
                (ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.BINDING_ERRORS));

        ApiError apiError = new ApiError(
                (Integer) attributes.get("status"),
                (String) attributes.get("message"),
                (String) attributes.get("path")
        );

        if (attributes.containsKey("errors")) {
            apiError.setValidationErrors(
                    new HashMap<String, String>() {
                        private static final long serialVersionUID = 4072361192174418949L;
                        {
                            for (FieldError fieldError : (List<FieldError>) attributes.get("errors")) {
                                put(fieldError.getField(), fieldError.getDefaultMessage());
                            }
                        }
                    }
            );
        }

        return apiError;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
