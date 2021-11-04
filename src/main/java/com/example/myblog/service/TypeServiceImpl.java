package com.example.myblog.service;

import com.example.myblog.dao.TypeRepository;
import com.example.myblog.po.Type;
import javassist.NotFoundException;
import net.bytebuddy.TypeCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    private TypeRepository typeRepository;

    @Transactional//表示将这个方法放在事物里面（一般把增删改查的方法放在事物里面）
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }
    @Transactional
    @Override
    public Type getType(Long id) {
       return typeRepository.getOne(id);

    }
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);//返回的是一个page类型的数组
    }
    @Transactional
    @Override
    public Type updateType(Long id, Type type) throws NotFoundException {
        Type t = typeRepository.getOne(id);
        if(t == null){
            throw new NotFoundException("不存在该类型");
        }
        //实现更新替换操作，然后保存
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();//直接返回list
    }

    @Override
    public List<Type> listType(Integer size) {
        //构建一个排序对象
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size);
        return typeRepository.findTop(pageable);
    }
}
