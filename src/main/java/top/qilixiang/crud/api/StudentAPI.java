package top.qilixiang.crud.api;

import top.qilixiang.crud.entity.Student;
import top.qilixiang.crud.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by qilixiang on 2017/11/26.
*/
@RestController
@RequestMapping("/crud/student")
public class StudentAPI {

    /**
     * 操作成功
     */
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    @Resource
    private StudentService studentService;

    @PostMapping
    public String add(Student student) {
        studentService.save(student);
        return DEFAULT_SUCCESS_MESSAGE;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        studentService.deleteById(id);
        return DEFAULT_SUCCESS_MESSAGE;
    }

    @PutMapping
    public String update(Student student) {
        studentService.update(student);
        return DEFAULT_SUCCESS_MESSAGE;
    }
    @GetMapping("/{id}")
    public Student detail(@PathVariable Integer id) {
        Student student = studentService.findById(id);
        return student;
    }

    @GetMapping
    public PageInfo list(Integer pageNumber, Integer pageSize) {
        PageInfo pageInfo = studentService.findAll(pageNumber,pageSize);
        return pageInfo;
    }
}
