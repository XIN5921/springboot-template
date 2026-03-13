package org.example.week02.controller;

import jakarta.annotation.Resource;
import org.example.week02.dto.StudentAddDTO;
import org.example.week02.dto.StudentUpdateDTO;
import org.example.week02.entity.Student;
import org.example.week02.service.StudentService;
import org.example.week02.vo.StudentVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Resource
    private StudentService studentService;

    /**
     * 获取所有学生
     *
     * @return 所有学生数据
     */
    @GetMapping("/all")
    public List<StudentVO> getAllStudents() {
        return studentService.getAllStudents();
    }

    /**
     * 根据id获取学生
     *
     * @param id 学生ID
     * @return 学生VO数据
     */
    @GetMapping("/{id}")
    public StudentVO getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    /**
     * 删除学生
     *
     * @param id 学生ID
     */
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    /**
     * 根据名称模糊查询学生
     *
     * @param name 学生名称
     * @return 学生VO数据
     */
    @GetMapping()
    public List<StudentVO> getStudentByName(@RequestParam String name) {
        return studentService.getStudentByName(name);
    }

    /**
     * 添加学生
     *
     * @param studentAddDTO 新增学生的DTO数据
     */
    @PostMapping
    public String addStudent(@RequestBody StudentAddDTO studentAddDTO) {
        studentService.addStudent(studentAddDTO);
        return "添加成功";
    }

    /**
     * 修改学生
     *
     * @param id                学生ID
     * @param studentUpdateDTO 修改的学生DTO数据
     */
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDTO studentUpdateDTO) {
        studentService.updateStudent(id, studentUpdateDTO);
        return "修改成功";
    }
}