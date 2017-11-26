package top.qilixiang.crud.service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import top.qilixiang.crud.entity.StudentTeacher;
import top.qilixiang.crud.repository.StudentTeacherMapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
* 基于通用MyBatis Mapper插件的Service接口的实现
*/
public class StudentTeacherService{
    @Autowired
    protected StudentTeacherMapper mapper;

    private Class<StudentTeacher> modelClass;    // 当前泛型真实类型的Class

    public StudentTeacherService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<StudentTeacher>) pt.getActualTypeArguments()[0];
    }

    @Transactional(readOnly = false)
        public void save(StudentTeacher model) {
        mapper.insertSelective(model);
    }

    @Transactional(readOnly = false)
    public void save(List<StudentTeacher> models) {
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
    public void update(StudentTeacher model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    public StudentTeacher findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public StudentTeacher findBy(String property, Object value) throws TooManyResultsException {
        // 手动设置数据源
        // DataSourceKeyHolder.setDbType(DataSourceKeyHolder.DataSourceType.MASTER);
        try {
            StudentTeacher model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(property);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<StudentTeacher> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<StudentTeacher> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }


    public List<StudentTeacher> findAll() {
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
