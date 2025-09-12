package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

import java.util.Date;

/**
 * 报警模态更新发生日期无效异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class InvalidAlarmModalUpdateHappenedDateException extends HandlerException {

    private static final long serialVersionUID = -7204018678653281828L;

    private final LongIdKey sectionKey;
    private final Date oldHappenedDate;
    private final Date happenedDate;

    public InvalidAlarmModalUpdateHappenedDateException(
            LongIdKey sectionKey, Date oldHappenedDate, Date happenedDate
    ) {
        this.sectionKey = sectionKey;
        this.oldHappenedDate = oldHappenedDate;
        this.happenedDate = happenedDate;
    }

    public InvalidAlarmModalUpdateHappenedDateException(
            Throwable cause, LongIdKey sectionKey, Date oldHappenedDate, Date happenedDate
    ) {
        super(cause);
        this.sectionKey = sectionKey;
        this.oldHappenedDate = oldHappenedDate;
        this.happenedDate = happenedDate;
    }

    @Override
    public String getMessage() {
        return "报警模态更新发生日期无效, 部件: " + sectionKey + ", 发生日期最小值: " + oldHappenedDate +
                ", 发生日期实际值: " + happenedDate;
    }
}
