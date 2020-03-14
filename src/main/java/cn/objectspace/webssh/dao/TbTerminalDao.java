package cn.objectspace.webssh.dao;

import cn.objectspace.webssh.entity.TbTerminal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TbTerminal)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-14 12:37:25
 */
public interface TbTerminalDao {

    /**
     * 通过ID查询单条数据
     *
     * @param terminalId 主键
     * @return 实例对象
     */
    TbTerminal queryById(String terminalId);

    /**
     * 通过用户Id查询单条数据
     *
     * @param userId 用户Id
     * @return 实例对象
     */
    List<TbTerminal> queryByUserId(String userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TbTerminal> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbTerminal 实例对象
     * @return 对象列表
     */
    List<TbTerminal> queryAll(TbTerminal tbTerminal);

    /**
     * 新增数据
     *
     * @param tbTerminal 实例对象
     * @return 影响行数
     */
    int insert(TbTerminal tbTerminal);

    /**
     * 修改数据
     *
     * @param tbTerminal 实例对象
     * @return 影响行数
     */
    int update(TbTerminal tbTerminal);

    /**
     * 通过主键删除数据
     *
     * @param terminalId 主键
     * @return 影响行数
     */
    int deleteById(String terminalId);

}