package com.example.myblog.web.admin;

import com.example.myblog.po.Type;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    //Pageable必须是springframework下的
    //@PageableDefault是设置默认值（每页10条记录，用id进行排序，并且是倒序）
    //Model是前端展示模型
    //从数据库（dao层有一个findall的方法，然后把pageable传进去）中获取type对象，然后把他们封装成一个page类型的数组，最后将其转入到model中
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));//保存一个page类型的数组，用来给前端使用
        return "admin/types";
    }

    //代表返回一个“新增”的页面
    @GetMapping("/types/input")
    public String input(){
        return "admin/types_input";
    }
    //新增
    //这个方法用来接收表单的提交，提交的时候把含有name的对象封装为types对象传递到后端
    @PostMapping("/type")
    public String post(@Validated Type type, BindingResult result, RedirectAttributes attributes){
        //表示将这个type对象保存在数据库中
        Type t = typeService.saveType(type);
        //非空判断
        if(t == null){
            //没保存成功时，在分类列表中给出提示信息————》还要在前端写message组件
            attributes.addAttribute("message","操作失败");
        }else {
            attributes.addAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/input")
    //必须要有PathVariable，将{id}传递给Long id
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types_input";
    }
    //更新
    @PostMapping("/type/{id}")
    public String editpost(@Validated Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes, Model model) throws NotFoundException {

        //使用数据库中自带的方法：update
        Type t = typeService.updateType(id,type);
        if(t == null){
            model.addAttribute("message","更新失败");
        }else {
            model.addAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }
}
