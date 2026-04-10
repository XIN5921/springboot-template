package org.example.week06.service;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/10 16:21 // 创建日期+时间
 * @description 类描述信息
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.week06.entity.Special;


public interface SpecialService {

    /**
     * 根据标题获取专栏（分页）
     *
     * @param title    标题
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 专栏列表
     */
    Page<Special> selectByTitle(String title, int pageNum, int pageSize);

    /**
     * 获取专栏详情
     *
     * @param id 专栏ID
     * @return 专栏详情
     */
    Special getById(String id);
}