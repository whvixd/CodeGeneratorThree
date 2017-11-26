package ${basePackage}.api;

import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}API {

    /**
     * 操作成功
     */
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping
    public String add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return DEFAULT_SUCCESS_MESSAGE;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return DEFAULT_SUCCESS_MESSAGE;
    }

    @PutMapping
    public String update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return DEFAULT_SUCCESS_MESSAGE;
    }
    @GetMapping("/{id}")
    public ${modelNameUpperCamel} detail(@PathVariable Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ${modelNameLowerCamel};
    }

    @GetMapping
    public PageInfo list(Integer pageNumber, Integer pageSize) {
        PageInfo pageInfo = ${modelNameLowerCamel}Service.findAll(pageNumber,pageSize);
        return pageInfo;
    }
}
