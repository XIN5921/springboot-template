package org.example.week02.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.week02.constant.GenderEnum;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentAddDTO {
        private String name;
        private String avatar;
        private GenderEnum gender;
        private LocalDate birthday;
        private String mobile;
}
