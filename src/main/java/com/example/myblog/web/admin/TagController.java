package com.example.myblog.web.admin;

import com.example.myblog.po.Tag;
import com.example.myblog.po.Type;
import com.example.myblog.service.TagService;
import com.example.myblog.service.TypeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    //Pageable必须是springframework下的
    //@PageableDefault是设置默认值（每页10条记录，用id进行排序，并且是倒序）
    //Model是前端展示模型
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));//表示前端页面拿到page对象
        return "admin/tags";
    }

    //代表返回一个“新增”的页面
    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags_input";
    }
    //这个方法用来接收表单的提交，提交的时候把含有name的对象封装为types对象传递到后端
    @PostMapping("/tag")
    public String post(@Validated Tag tag, BindingResult result, RedirectAttributes attributes, Model model){
     /*   Type type1 = typeService.getTypeByName(type.getName());
        //后端自定义一个异常信息，就是校验分类名称是否重复————>在前端需要接收（ui error message）
        if(type1 != null){
            //用result返回错误给前端，验证的是name（固定的），【@Validated】而nameError是自定义的   s2是最终要返回的一个信息
            result.rejectValue("name","nameError","不能添加重复分类");
        }*/
        Tag t = tagService.saveTag(tag);
        if(t == null){
            //没保存成功时，在分类列表中给出提示信息————》还要在前端写message组件
            model.addAttribute("message","操作失败");
        }else {
            model.addAttribute("message","操作成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    //必须要有PathVariable，将{id}传递给Long id
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags_input";
    }
    @PostMapping("/tag/{id}")
    public String editpost(@Validated Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes, Model model) throws NotFoundException {
     /*   Type type1 = typeService.getTypeByName(type.getName());
        //后端自定义一个异常信息，就是校验分类名称是否重复————>在前端需要接收（ui error message）
        if(type1 != null){
            //用result返回错误给前端，验证的是name（固定的），【@Validated】而nameError是自定义的   s2是最终要返回的一个信息
            result.rejectValue("name","nameError","不能添加重复分类");
        }*/
        Tag t = tagService.updateTag(id,tag);
        if(t == null){
            //没保存成功时，在分类列表中给出提示信息————》还要在前端写message组件
            model.addAttribute("message","更新失败");
        }else {
            model.addAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        return "redirect:/admin/tags" ;
    }
}
