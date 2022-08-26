package com.nar.halisaha.Mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private List<String> recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
