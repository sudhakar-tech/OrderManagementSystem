package com.wellsfargo.springbootdatajpa.exceptions;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "error")
public class ErrorResponse 
{
    //General error message about nature of error
    private String message;
    //Specific errors in API request processing
    private List<String> details;
}
