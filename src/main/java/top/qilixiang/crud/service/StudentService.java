package top.qilixiang.crud.service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import top.qilixiang.crud.entity.Student;
import top.qilixiang.crud.repository.StudentMapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
* 基于通用MyBatis Mapper插件的Service接口的实现
*/
public class StudentService{
    @Autowired
    protected StudentMapper mapper;

    private Class<Student> modelClass;    // 当前泛型真实类型的Class

    public StudentService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<Student>) pt.getActualTypeArguments()[0];
    }

    @Transactional(readOnly = false)
        public void save(Student model) {
        mapper.insertSelective(model);
    }

    @Transactional(readOnly = false)
    public void save(List<Student> models) {
        mapper.insertList(models);
    }

    @Transactional(readOnly = false)
        public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
        public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    @Transactional(readOnly = false)
    public void update(Student model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    public Student findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public Student findBy(String property, Object value) throws TooManyResultsException {
        // 手动设置数据源
        // DataSourceKeyHolder.setDbType(DataSourceKeyHolder.DataSourceType.MASTER);
        try {
            Student model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(property);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<Student> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<Student> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }


    public List<Student> findAll() {
        return mapper.selectAll();
    }

    public int count() {
        return mapper.selectCount(null);
    }


    public PageInfo findAll(Integer pageNumber, Integer pageSize) {
        return PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                mapper.selectAll();
            }
        });
    }
}
