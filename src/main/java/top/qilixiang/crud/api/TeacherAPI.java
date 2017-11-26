package top.qilixiang.crud.api;

import top.qilixiang.crud.entity.Teacher;
import top.qilixiang.crud.service.TeacherService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by qilixiang on 2017/11/26.
*/
@RestController
@RequestMapping("/crud/teacher")
public class TeacherAPI {

    /**
     * 操作成功
     */
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    @Resource
    private TeacherService teacherService;

    @PostMapping
    public String add(Teacher teacher) {
        teacherService.save(teacher);
        return DEFAULT_SUCCESS_MESSAGE;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        teacherService.deleteById(id);
        return DEFAULT_SUCCESS_MESSAGE;
    }

    @PutMapping
    public String update(Teacher teacher) {
        teacherService.update(teacher);
        return DEFAULT_SUCCESS_MESSAGE;
    }
    @GetMapping("/{id}")
    public Teacher detail(@PathVariable Integer id) {
        Teacher teacher = teacherService.findById(id);
        return teacher;
    }

    @GetMapping
    public PageInfo list(Integer pageNumber, Integer pageSize) {
        PageInfo pageInfo = teacherService.findAll(pageNumber,pageSize);
        return pageInfo;
    }
}
