package ru.yescoding.app.model.dto;

import java.sql.Date;
import java.time.LocalDate;

public record SuccessDto (String testName, String done, int donePercent, String subject, LocalDate finishDate){
}
