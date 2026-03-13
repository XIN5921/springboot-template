package org.example.week02.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.week02.constant.GenderEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String name;
    private String avatar;
    private GenderEnum gender;
    private LocalDate birthday;
    private boolean enabled;
    private String mobile;
    private LocalDateTime createTime;
}
