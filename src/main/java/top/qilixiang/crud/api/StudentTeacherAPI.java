package top.qilixiang.crud.api;

import top.qilixiang.crud.entity.StudentTeacher;
import top.qilixiang.crud.service.StudentTeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by qilixiang on 2017/11/26.
*/
@RestController
@RequestMapping("/crud/student/teacher")
public class StudentTeacherAPI {

    /**
     * 操作成功
     */
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    @Resource
    private StudentTeacherService studentTeacherService;

    @PostMapping
    public String add(StudentTeacher studentTeacher) {
        studentTeacherService.save(studentTeacher);
        return DEFAULT_SUCCESS_MESSAGE;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        studentTeacherService.deleteById(id);
        return DEFAULT_SUCCESS_MESSAGE;
    }

    @PutMapping
    public String update(StudentTeacher studentTeacher) {
        studentTeacherService.update(studentTeacher);
        return DEFAULT_SUCCESS_MESSAGE;
    }
    @GetMapping("/{id}")
    public StudentTeacher detail(@PathVariable Integer id) {
        StudentTeacher studentTeacher = studentTeacherService.findById(id);
        return studentTeacher;
    }

    @GetMapping
    public PageInfo list(Integer pageNumber, Integer pageSize) {
        PageInfo pageInfo = studentTeacherService.findAll(pageNumber,pageSize);
        return pageInfo;
    }
}
