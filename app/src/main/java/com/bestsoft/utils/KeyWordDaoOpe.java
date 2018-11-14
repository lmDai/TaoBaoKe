package com.bestsoft.utils;

import android.content.Context;

import com.bestsoft.bean.KeyWordModel;
import com.bestsoft.gen.KeyWordModelDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @package: com.bestsoft.utils
 * @user:xhkj
 * @date:2018/11/14
 * @description:
 **/
public class KeyWordDaoOpe {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param stu
     */
    public static void insertData(Context context, KeyWordModel stu) {
        DbManager.getDaoSession(context).getKeyWordModelDao().insert(stu);
    }


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<KeyWordModel> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getKeyWordModelDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param KeyWordModel
     */
    public static void saveData(Context context, KeyWordModel KeyWordModel) {
        DbManager.getDaoSession(context).getKeyWordModelDao().save(KeyWordModel);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param KeyWordModel 删除具体内容
     */
    public static void deleteData(Context context, KeyWordModel KeyWordModel) {
        DbManager.getDaoSession(context).getKeyWordModelDao().delete(KeyWordModel);
    }


    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getKeyWordModelDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param KeyWordModel
     */
    public static void updateData(Context context, KeyWordModel KeyWordModel) {
        DbManager.getDaoSession(context).getKeyWordModelDao().update(KeyWordModel);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<KeyWordModel> queryAll(Context context) {
        QueryBuilder<KeyWordModel> builder = DbManager.getDaoSession(context).getKeyWordModelDao().queryBuilder();

        return builder.build().list();
    }


    /**
     * 分页加载
     *
     * @param context
     * @param pageSize 当前第几页(程序中动态修改pageSize的值即可)
     * @param pageNum  每页显示多少个
     * @return
     */
    public static List<KeyWordModel> queryPaging(int pageSize, int pageNum, Context context) {
        KeyWordModelDao KeyWordModelDao = DbManager.getDaoSession(context).getKeyWordModelDao();
        List<KeyWordModel> listMsg = KeyWordModelDao.queryBuilder()
                .offset(pageSize * pageNum).limit(pageNum).list();
        return listMsg;

    }
}
