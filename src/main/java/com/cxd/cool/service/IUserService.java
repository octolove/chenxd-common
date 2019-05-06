package com.cxd.cool.service;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxd.cool.domain.PageBean;
import com.cxd.cool.domain.UserInfo;
import com.cxd.cool.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public UserInfo findUserInfoById(Integer id) {
        return userMapper.findUserInfo(id);
    }

    /**
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     */
    public PageBean<UserInfo> list(int pageNum, int pageSize) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageNum = 5;
        }

        /**
         * 用PageInfo对结果进行包装,/紧跟着的第一个select方法会被分页,
         * 后面的不会被分页，除非再次调用PageHelper.startPage
         */
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> list = userMapper.findAllUserInfo();
        PageInfo<UserInfo> page = new PageInfo<UserInfo>(list);

        // 分装结果集
        PageBean<UserInfo> pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setPageNum(page.getPageNum());
        pageBean.setPageSize(page.getPageSize());
        pageBean.setPages(page.getPages());
        pageBean.setNextPage(page.getNextPage());
        pageBean.setPrePage(page.getPrePage());
        pageBean.setHasNextPage(page.isHasNextPage());
        pageBean.setHasPreviousPage(page.isHasPreviousPage());
        pageBean.setIsFirstPage(page.isIsFirstPage());
        pageBean.setIsLastPage(page.isHasNextPage());
        return pageBean;
    }

    public void batchInsert() {
        // 关闭session的自动提交
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        // 利用反射生成mapper对象
        UserMapper umapper = session.getMapper(UserMapper.class);
        // try {
        // int i = 0;
        // for (Fenshu fs : fenshuList) {
        // excelMapper.saveFenshu(tableName, courseName, current, fs.getXuehao(), fs.getShijuanming(),
        // fs.getDenglushijian(), fs.getJiaojuanshijian(), fs.getShitileixing(),
        // fs.getShitixuhao(), fs.getShititikuhao(), fs.getShitifenzhi(), fs.getXueshengdefen(), fs.getDatiyongshi());
        // if (i % 1000 == 0 || i == fenshuList.size() - 1) {
        // // 手动每1000个一提交，提交后无法回滚
        // session.commit();
        // session.clearCache();// 注意，如果没有这个动作，可能会导致内存崩溃。
        // }
        // i++;
        // }
        // } catch (Exception e) {
        // // 没有提交的数据可以回滚
        // session.rollback();
        // } finally {
        // session.close();
        // }
    }
}
