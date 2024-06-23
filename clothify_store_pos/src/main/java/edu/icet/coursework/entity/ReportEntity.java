package edu.icet.coursework.entity;

import edu.icet.coursework.util.ReportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "report")
@SoftDelete
public class ReportEntity {
    @Id
    @Column(name = "report_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "report_id_generator"
    )
    @SequenceGenerator(
            name = "report_id_generator",
            allocationSize = 1
    )
    private Integer reportId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "report_type")
    private ReportType reportType;

    @Column(name = "report_date_time")
    private LocalDateTime reportDateTime;
}
