package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.List;

/**
 * 驱动器本地缓存处理器。
 * <p>处理器在本地保存数据，缓存中的数据可以保证与数据源保持同步。</p>
 * <p>数据存放在本地，必要时才与数据访问层通信，这有助于程序效率的提升。</p>
 * <p>该处理器线程安全。</p>
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface AssignLocalCacheHandler extends Handler {

    /**
     * 获取所有的部件主键。
     *
     * @return 所有的部件主键组成的列表。
     * @throws HandlerException 处理器异常。
     */
    List<LongIdKey> getSectionKeys() throws HandlerException;

    /**
     * 获取指定部件的记录上下文。
     *
     * @param sectionKey 指定部件的记录上下文，或者是null。
     * @return 指定部件的记录上下文。
     * @throws HandlerException 处理器异常。
     */
    DriveContext getDriveContext(LongIdKey sectionKey) throws HandlerException;

    /**
     * 清除本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clear() throws HandlerException;

    /**
     * 数据记录上下文。
     *
     * @author DwArFeng
     * @since beta-1.0.0
     */
    class DriveContext implements Bean {

        private static final long serialVersionUID = -1269757566394917803L;

        private Section section;
        private List<DriverInfo> driverInfos;

        public DriveContext() {
        }

        public DriveContext(Section section, List<DriverInfo> driverInfos) {
            this.section = section;
            this.driverInfos = driverInfos;
        }

        public Section getSection() {
            return section;
        }

        public void setSection(Section section) {
            this.section = section;
        }

        public List<DriverInfo> getDriverInfos() {
            return driverInfos;
        }

        public void setDriverInfos(List<DriverInfo> driverInfos) {
            this.driverInfos = driverInfos;
        }

        @Override
        public String toString() {
            return "DriveContext{" +
                    "section=" + section +
                    ", driverInfos=" + driverInfos +
                    '}';
        }
    }
}
