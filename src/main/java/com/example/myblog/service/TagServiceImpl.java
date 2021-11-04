package com.example.myblog.service;

import com.example.myblog.dao.TagRepository;
import com.example.myblog.dao.TypeRepository;
import com.example.myblog.po.Tag;
import com.example.myblog.po.Type;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) throws NotFoundException {
        Tag t = tagRepository.getOne(id);
        if(t == null){
            throw new NotFoundException("不存在该类型");
        }
        //实现更新替换操作，然后保存
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> listTag(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    //将字符串转化为一个数组
    private  List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if("".equals(ids) && ids != null){
            String[] idarray = ids.split(",");
            for(int i = 0;i < idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
