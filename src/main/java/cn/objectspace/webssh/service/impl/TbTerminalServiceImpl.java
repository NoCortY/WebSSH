package cn.objectspace.webssh.service.impl;

import cn.objectspace.webssh.entity.TbTerminal;
import cn.objectspace.webssh.dao.TbTerminalDao;
import cn.objectspace.webssh.service.TbTerminalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbTerminal)表服务实现类
 *
 * @author makejava
 * @since 2020-03-14 12:37:27
 */
@Service("tbTerminalService")
public class TbTerminalServiceImpl implements TbTerminalService {
    @Resource
    private TbTerminalDao tbTerminalDao;

    /**
     * 通过ID查询单条数据
     *
     * @param terminalId 主键
     * @return 实例对象
     */
    @Override
    public TbTerminal queryById(String terminalId) {
        return this.tbTerminalDao.queryById(terminalId);
    }

    /**
     * 通过用户Id查询单条数据
     *
     * @param userId 用户Id
     * @return 实例对象
     */
    @Override
    public List<TbTerminal>  queryByUserId(String userId) {
        return this.tbTerminalDao.queryByUserId(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TbTerminal> queryAllByLimit(int offset, int limit) {
        return this.tbTerminalDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbTerminal 实例对象
     * @return 实例对象
     */
    @Override
    public TbTerminal insert(TbTerminal tbTerminal) {
        this.tbTerminalDao.insert(tbTerminal);
        return tbTerminal;
    }

    /**
     * 修改数据
     *
     * @param tbTerminal 实例对象
     * @return 实例对象
     */
    @Override
    public TbTerminal update(TbTerminal tbTerminal) {
        this.tbTerminalDao.update(tbTerminal);
        return this.queryById(tbTerminal.getTerminalId());
    }

    /**
     * 通过主键删除数据
     *
     * @param terminalId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String terminalId) {
        return this.tbTerminalDao.deleteById(terminalId) > 0;
    }
}