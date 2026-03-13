package org.example.week02.service;

import org.example.week02.constant.GenderEnum;
import org.example.week02.dto.StudentAddDTO;
import org.example.week02.dto.StudentUpdateDTO;
import org.example.week02.entity.Student;
import org.example.week02.vo.StudentVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {
    private static final Map<Long, Student> STUDENT_DATA = new ConcurrentHashMap<>();

    /*
     * 静态初始化数据
     */
    static {
        Student student1 = Student.builder()
                .id(1001L)
                .name("张三")
                .mobile("13888888888")
                .gender(GenderEnum.MALE)
                .avatar("https://mqxu.top/1.png")
                .enabled(true)
                .birthday(LocalDate.of(1990, 1, 1))
                .createTime(LocalDateTime.now())
                .build();
        Student student2 = Student.builder()
                .id(1002L)
                .name("张三丰")
                .mobile("13888888889")
                .gender(GenderEnum.FEMALE)
                .avatar("https://mqxu.top/2.png")
                .enabled(true)
                .birthday(LocalDate.of(1990, 1, 1))
                .createTime(LocalDateTime.now())
                .build();
        STUDENT_DATA.put(student1.getId(), student1);
        STUDENT_DATA.put(student2.getId(), student2);
    }

    /**
     * 获取所有学生
     *
     * @return 所有学生数据
     */
    public List<StudentVO> getAllStudents() {
        List<StudentVO> list = new ArrayList<>();
        STUDENT_DATA.values().forEach(student -> {
            list.add(StudentVO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .mobile(student.getMobile())
                    .gender(student.getGender())
                    .createTime(student.getCreateTime())
                    .build());
        });
        return list;
    }

    /**
     * 添加学生
     *
     * @param studentAddDTO 新增学生的DTO数据
     */
    public void addStudent(StudentAddDTO studentAddDTO) {
        Student student = Student.builder()
                // id自增，这里使用系统时间
                .id(System.currentTimeMillis())
                .name(studentAddDTO.getName())
                .mobile(studentAddDTO.getMobile())
                .gender(studentAddDTO.getGender())
                .avatar(studentAddDTO.getAvatar())
                .enabled(true)
                .birthday(studentAddDTO.getBirthday())
                .createTime(LocalDateTime.now())
                .build();
        STUDENT_DATA.put(student.getId(), student);
    }

    /**
     * 根据id获取学生
     *
     * @param id 学生ID
     * @return 学生VO数据
     */
    public StudentVO getStudent(Long id) {
        Student student = STUDENT_DATA.get(id);
        if (student == null) {
            throw new RuntimeException("学生不存在，ID: " + id);
        }
        return StudentVO.builder()
                .id(student.getId())
                .name(student.getName())
                .mobile(student.getMobile())
                .gender(student.getGender())
                .createTime(student.getCreateTime())
                .build();
    }

    /**
     * 根据名称模糊查询学生
     *
     * @param name 学生名称
     * @return 学生VO数据
     */
    public List<StudentVO> getStudentByName(String name) {
        List<StudentVO> list = new ArrayList<>();
        STUDENT_DATA.values().forEach(student -> {
            if (student.getName().contains(name)) {
                list.add(StudentVO.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .mobile(student.getMobile())
                        .gender(student.getGender())
                        .createTime(student.getCreateTime())
                        .build());
            }
        });
        return list;
    }

    /**
     * 修改学生
     *
     * @param id                学生ID
     * @param studentUpdateDTO 修改的学生DTO数据
     */
    public void updateStudent(Long id, StudentUpdateDTO studentUpdateDTO) {
        Student student = STUDENT_DATA.get(id);
        student.setName(studentUpdateDTO.getName());
        student.setMobile(studentUpdateDTO.getMobile());
        student.setAvatar(studentUpdateDTO.getAvatar());
    }

    /**
     * 删除学生
     *
     * @param id 学生ID
     */
    public void deleteStudent(Long id) {
        STUDENT_DATA.remove(id);
    }
}
