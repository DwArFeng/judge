package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.List;

/**
 * 评估用本地缓存处理器。
 * <p>处理器在本地保存数据，缓存中的数据可以保证与数据源保持同步。</p>
 * <p>数据存放在本地，必要时才与数据访问层通信，这有助于程序效率的提升。</p>
 * <p>该处理器线程安全。</p>
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface EvaluateLocalCacheHandler extends Handler {

    /**
     * 是否包含指定的部件。
     *
     * @param sectionKey 指定的部件。
     * @return 是否包含指定的部件。
     * @throws HandlerException 处理器异常。
     */
    boolean existsSection(LongIdKey sectionKey) throws HandlerException;

    /**
     * 获取指定部件的评估上下文。
     *
     * @param sectionKey 指定部件的评估上下文，或者是null。
     * @return 指定部件的评估上下文。
     * @throws HandlerException 处理器异常。
     */
    EvaluateContext getEvaluateContext(LongIdKey sectionKey) throws HandlerException;

    /**
     * 清除本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clear() throws HandlerException;

    /**
     * 数据评估上下文。
     *
     * @author DwArFeng
     * @since beta-1.0.0
     */
    class EvaluateContext implements Bean {

        private static final long serialVersionUID = 8224658804994085875L;

        private Section section;
        private List<Judger> judgers;

        public EvaluateContext() {
        }

        public EvaluateContext(Section section, List<Judger> judgers) {
            this.section = section;
            this.judgers = judgers;
        }

        public Section getSection() {
            return section;
        }

        public void setSection(Section section) {
            this.section = section;
        }

        public List<Judger> getJudgers() {
            return judgers;
        }

        public void setJudgers(List<Judger> judgers) {
            this.judgers = judgers;
        }

        @Override
        public String toString() {
            return "EvaluateContext{" +
                    "section=" + section +
                    ", judgers=" + judgers +
                    '}';
        }
    }
}
