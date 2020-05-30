package com.zjgsu.dao;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;

/**
 * 通用DAO接口
 *
 * @param <T>
 * @author 牧峰 2018-10-17 16:49:54
 */
@SuppressWarnings("rawtypes")
public interface BaseDao<T> {

    /**
     * 保存新记录
     */
    String save(T t);

    /**
     * 根据主键查询
     */
    T getById(Serializable id);

    /**
     * 更新记录
     */
    void update(T t);

    /**
     * HQL更新
     */
    int updateByHql(String hql, Object... params);

    /**
     * HQL更新，重载
     */
    int updateByHql(String hql, List<Object> params);

    /**
     * SQL更新
     */
    int updateBySql(String sql, Object... params);

    /**
     * SQL更新，重载
     */
    int updateBySql(String sql, List<Object> params);

    /**
     * QBC单表查询单个数据
     */
    T getByCriterion(Criterion... criterions);

    /**
     * QBC单表查询总记录数
     */
    int countByCriterion(Criterion... criterions);

    /**
     * QBC单表查询集合
     */
    List<T> listByCriterion(Criterion... criterions);

    /**
     * QBC单表分页查询集合
     */
    List<T> listPageByCriterion(Integer pageNum, Integer rowCount, Criterion... criterions);

    /**
     * QBC单表分页排序查询集合
     */
    List<T> listPageByCriterionAndOrder(Integer pageNum, Integer rowCount, List<Order> orders, Criterion... criterions);

    /**
     * HQL单表查询单个数据
     */
    T getByHqlQuery(String hql, Object... params);

    /**
     * HQL单表查询单个数据，重载
     */
    T getByHqlQuery(String hql, List<Object> params);

    /**
     * HQL单表或多表查询单个数据或封装的对象，若为单个数据必须为单列数据!!!!!
     */
    Object getObjectByHqlQuery(String hql, Object... params);

    /**
     * HQL单表或多表查询单个数据或封装的对象，重载，若为单个数据必须为单列数据!!!!!
     */
    Object getObjectByHqlQuery(String hql, List<Object> params);

    /**
     * HQL单表或多表查询部分数据，不得为单列数据!!!!!
     */
    Object[] getObjectArrByHqlQuery(String hql, Object... params);

    /**
     * HQL单表或多表查询部分数据，重载，不得为单列数据!!!!!
     */
    Object[] getObjectArrByHqlQuery(String hql, List<Object> params);

    /**
     * HQL单表或多表查询总记录数
     */
    int countByHqlQuery(String hql, Object... params);

    /**
     * HQL单表或多表查询总记录数，重载
     */
    int countByHqlQuery(String hql, List<Object> params);

    /**
     * HQL单表查询数据集合
     */
    List<T> listByHqlQuery(String hql, Object... params);

    /**
     * HQL单表查询数据集合，重载
     */
    List<T> listByHqlQuery(String hql, List<Object> params);

    /**
     * HQL单表分页查询数据集合
     */
    List<T> listPageByHqlQuery(String hql, Integer pageNum, Integer rowCount, Object... params);

    /**
     * HQL单表分页查询数据集合，重载
     */
    List<T> listPageByHqlQuery(String hql, Integer pageNum, Integer rowCount, List<Object> params);

    /**
     * HQL单表或多表查询单个数据集合或者封装的对象，若为单个数据必须为单列数据!!!!!
     */
    List listObjectByHqlQuery(String hql, Object... params);

    /**
     * HQL单表或多表查询单个数据集合或者封装的对象，重载，若为单个数据必须为单列数据!!!!!
     */
    List listObjectByHqlQuery(String hql, List<Object> params);

    /**
     * HQL单表或多表分页查询单个数据集合或者封装的对象，若为单个数据必须为单列数据!!!!!
     */
    List listPageObjectByHqlQuery(String hql, Integer pageNum, Integer rowCount, Object... params);

    /**
     * HQL单表或多表分页查询单个数据集合或者封装的对象，重载，若为单个数据必须为单列数据!!!!!
     */
    List listPageObjectByHqlQuery(String hql, Integer pageNum, Integer rowCount, List<Object> params);

    /**
     * HQL单表或多表查询部分数据集合，不得为单列数据!!!!!
     */
    List<Object[]> listObjectArrByHqlQuery(String hql, Object... params);

    /**
     * HQL单表或多表查询部分数据集合，重载，不得为单列数据!!!!!
     */
    List<Object[]> listObjectArrByHqlQuery(String hql, List<Object> params);

    /**
     * HQL单表或多表分页查询部分数据集合，不得为单列数据!!!!!
     */
    List<Object[]> listPageObjectArrByHqlQuery(String hql, Integer pageNum, Integer rowCount, Object... params);

    /**
     * HQL单表或多表分页查询部分数据集合，重载，不得为单列数据!!!!!
     */
    List<Object[]> listPageObjectArrByHqlQuery(String hql, Integer pageNum, Integer rowCount, List<Object> params);

    /**
     * SQL单表或多表查询单个数据，必须为单列数据!!!!!
     */
    Object getObjectBySqlQuery(String sql, Object... params);

    /**
     * SQL单表或多表查询单个数据，重载，必须为单列数据!!!!!
     */
    Object getObjectBySqlQuery(String sql, List<Object> params);

    /**
     * SQL单表或多表查询单个Object数组对象，不得为单列数据!!!!!
     */
    Object[] getObjectArrBySqlQuery(String sql, Object... params);

    /**
     * SQL单表或多表查询单个Object数组对象，重载，不得为单列数据!!!!!
     */
    Object[] getObjectArrBySqlQuery(String sql, List<Object> params);

    /**
     * SQL单表或多表查询总记录数
     */
    int countBySqlQuery(String sql, Object... params);

    /**
     * SQL单表或多表查询总记录数，重载
     */
    int countBySqlQuery(String sql, List<Object> params);

    /**
     * SQL单表或多表查询单个数据，必须为单列数据!!!!!
     */
    List listObjectBySqlQuery(String sql, Object... params);

    /**
     * SQL单表或多表查询单个数据，重载，必须为单列数据!!!!!
     */
    List listObjectBySqlQuery(String sql, List<Object> params);

    /**
     * SQL单表或多表分页查询单个数据，必须为单列数据!!!!!
     */
    List listPageObjectBySqlQuery(String sql, Integer pageNum, Integer rowCount, Object... params);

    /**
     * SQL单表或多表分页查询单个数据，重载，必须为单列数据!!!!!
     */
    List listPageObjectBySqlQuery(String sql, Integer pageNum, Integer rowCount, List<Object> params);

    /**
     * SQL单表或多表查询单个Object数组集合，不得为单列数据!!!!!
     */
    List<Object[]> listObjectArrBySqlQuery(String sql, Object... params);

    /**
     * SQL单表或多表查询单个Object数组集合，重载，不得为单列数据!!!!!
     */
    List<Object[]> listObjectArrBySqlQuery(String sql, List<Object> params);

    /**
     * SQL单表或多表分页查询单个Object数组集合，不得为单列数据!!!!!
     */
    List<Object[]> listPageObjectArrBySqlQuery(String sql, Integer pageNum, Integer rowCount, Object... params);

    /**
     * SQL单表或多表分页查询单个Object数组集合，重载，不得为单列数据!!!!!
     */
    List<Object[]> listPageObjectArrBySqlQuery(String sql, Integer pageNum, Integer rowCount, List<Object> params);

    /**
     * SQL单表或多表查询单个自定义对象，支持多表SQL查询
     */
    <D> D getDTOBySqlQuery(String sql, Class<D> clazz, Object... params);

    /**
     * SQL单表或多表查询单个自定义对象，重载，支持多表SQL查询
     */
    <D> D getDTOBySqlQuery(String sql, Class<D> clazz, List<Object> params);

    /**
     * SQL单表或多表查询自定义对象集合，支持多表SQL查询
     */
    <D> List<D> listDTOBySqlQuery(String sql, Class<D> clazz, Object... params);

    /**
     * SQL单表或多表查询自定义对象集合，重载，支持多表SQL查询
     */
    <D> List<D> listDTOBySqlQuery(String sql, Class<D> clazz, List<Object> params);

    /**
     * SQL单表或多表查询自定义对象集合，支持多表SQL查询
     */
    <D> List<D> listPageDTOBySqlQuery(String sql, Class<D> clazz, Integer pageNum, Integer rowCount, Object... params);

    /**
     * SQL单表或多表查询自定义对象集合，支持多表SQL查询
     */
    <D> List<D> listPageDTOBySqlQuery(String sql, Class<D> clazz, Integer pageNum, Integer rowCount, List<Object> params);

}
