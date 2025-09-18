package com.dwarfeng.judge.sdk.handler.provider;

import com.dwarfeng.judge.stack.exception.ProviderSessionException;
import com.dwarfeng.judge.stack.handler.ProviderSession;

import java.util.List;
import java.util.Map;

/**
 * 提供器会话的抽象实现。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public abstract class AbstractProviderSession implements ProviderSession {

    @Override
    public void openSession() throws ProviderSessionException {
        try {
            doOpenSession();
        } catch (Exception e) {
            throw ExceptionHelper.parseProviderSessionException(e);
        }
    }

    /**
     * 打开提供器会话。
     *
     * @throws Exception 任何可能的异常。
     * @see #openSession()
     */
    protected abstract void doOpenSession() throws Exception;

    @Override
    public void closeSession() throws ProviderSessionException {
        try {
            doCloseSession();
        } catch (Exception e) {
            throw ExceptionHelper.parseProviderSessionException(e);
        }
    }

    /**
     * 关闭提供器会话。
     *
     * @throws Exception 任何可能的异常。
     * @see #closeSession()
     */
    protected abstract void doCloseSession() throws Exception;

    @Override
    public List<Map<String, Object>> provide(String preset, Object[] objs) throws ProviderSessionException {
        try {
            return doProvide(preset, objs);
        } catch (Exception e) {
            throw ExceptionHelper.parseProviderSessionException(e);
        }
    }

    /**
     * 提供数据。
     *
     * <p>
     * 方法中的 <code>preset</code> 参数用于区分数据的提供方式。<br>
     * 例如一个提供器可以提供常规历史数据和报警历史数据，则这两种数据应该用不同的 <code>preset</code> 加以区分。
     *
     * <p>
     * 方法中的 <code>objs</code> 参数用于指定预设的参数，提供器根据参数提供对应的数据。<br>
     * 例如一个提供器可以根据时间范围提供历史数据，则 <code>objs</code> 中应该包含两个参数，分别表示开始时间和结束时间。
     *
     * <p>
     * 方法中的 <code>objs</code> 参数中的每一个对象应该是简单对象，例如字符串、数字等。<br>
     * 禁止使用无法序列化的复杂对象，例如数据库连接、文件句柄等。
     *
     * <p>
     * 提供器应明确地在描述或文档中说明每个预设所需的参数类型和顺序。<br>
     * 如果提供器无法识别指定的预设，或者提供的参数不符合预设的要求，则应抛出异常。
     *
     * @param preset 预设。
     * @param objs   参数对象数组。
     * @return 提供结果列表。
     * @throws Exception 任何可能的异常。
     * @see #provide(String, Object[])
     */
    protected abstract List<Map<String, Object>> doProvide(String preset, Object[] objs) throws Exception;
}
