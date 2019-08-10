package com.xuyang.gdutmallserver.controller;

import com.xuyang.gdutmallserver.domain.BaseResp;
import com.xuyang.gdutmallserver.domain.GetCategoryReq;
import com.xuyang.gdutmallserver.model.Category;
import com.xuyang.gdutmallserver.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.xuyang.gdutmallserver.controller.BaseController.DEFAULT_JSON_CONTENT_TYPE;

@Controller
@RequestMapping(produces = {DEFAULT_JSON_CONTENT_TYPE}, value = {"/category"})
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/getCategory"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<Category>> getCategory(@RequestBody GetCategoryReq req) {
        BaseResp resp = new BaseResp();

        List list = this.categoryService.getCategoryList(req.getParentId());
        if ((list == null) || (list.size() == 0)) {
            resp.setStatus(0);
            resp.setMessage("分类列表为空");
            return resp;
        }

        resp.setStatus(0);
        resp.setMessage("分类列表获取成功");
        resp.setData(list);
        return resp;
    }
}