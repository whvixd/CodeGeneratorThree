package ${basePackage}.service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.repository.${modelNameUpperCamel}Mapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
* 基于通用MyBatis Mapper插件的Service接口的实现
*/
public class ${modelNameUpperCamel}Service{
    @Autowired
    protected ${modelNameUpperCamel}Mapper mapper;

    private Class<${modelNameUpperCamel}> modelClass;    // 当前泛型真实类型的Class

    public ${modelNameUpperCamel}Service() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<${modelNameUpperCamel}>) pt.getActualTypeArguments()[0];
    }

    @Transactional(readOnly = false)
        public void save(${modelNameUpperCamel} model) {
        mapper.insertSelective(model);
    }

    @Transactional(readOnly = false)
    public void save(List<${modelNameUpperCamel}> models) {
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
    public void update(${modelNameUpperCamel} model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    public ${modelNameUpperCamel} findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public ${modelNameUpperCamel} findBy(String property, Object value) throws TooManyResultsException {
        // 手动设置数据源
        // DataSourceKeyHolder.setDbType(DataSourceKeyHolder.DataSourceType.MASTER);
        try {
            ${modelNameUpperCamel} model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(property);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<${modelNameUpperCamel}> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<${modelNameUpperCamel}> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }


    public List<${modelNameUpperCamel}> findAll() {
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
