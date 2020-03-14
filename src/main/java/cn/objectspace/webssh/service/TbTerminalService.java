package cn.objectspace.webssh.service;

import cn.objectspace.webssh.entity.TbTerminal;

import java.util.List;

/**
 * (TbTerminal)表服务接口
 *
 * @author makejava
 * @since 2020-03-14 12:37:26
 */
public interface TbTerminalService {

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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TbTerminal> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbTerminal 实例对象
     * @return 实例对象
     */
    TbTerminal insert(TbTerminal tbTerminal);

    /**
     * 修改数据
     *
     * @param tbTerminal 实例对象
     * @return 实例对象
     */
    TbTerminal update(TbTerminal tbTerminal);

    /**
     * 通过主键删除数据
     *
     * @param terminalId 主键
     * @return 是否成功
     */
    boolean deleteById(String terminalId);

}