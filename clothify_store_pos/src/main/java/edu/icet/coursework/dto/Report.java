package edu.icet.coursework.dto;


import edu.icet.coursework.util.ReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private Integer reportId;
    private User user;
    private ReportType reportType;
    private LocalDateTime reportDateTime;
}
