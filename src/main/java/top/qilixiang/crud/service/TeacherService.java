package top.qilixiang.crud.service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import top.qilixiang.crud.entity.Teacher;
import top.qilixiang.crud.repository.TeacherMapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
* 基于通用MyBatis Mapper插件的Service接口的实现
*/
public class TeacherService{
    @Autowired
    protected TeacherMapper mapper;

    private Class<Teacher> modelClass;    // 当前泛型真实类型的Class

    public TeacherService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<Teacher>) pt.getActualTypeArguments()[0];
    }

    @Transactional(readOnly = false)
        public void save(Teacher model) {
        mapper.insertSelective(model);
    }

    @Transactional(readOnly = false)
    public void save(List<Teacher> models) {
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
    public void update(Teacher model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    public Teacher findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public Teacher findBy(String property, Object value) throws TooManyResultsException {
        // 手动设置数据源
        // DataSourceKeyHolder.setDbType(DataSourceKeyHolder.DataSourceType.MASTER);
        try {
            Teacher model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(property);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<Teacher> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<Teacher> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }


    public List<Teacher> findAll() {
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
