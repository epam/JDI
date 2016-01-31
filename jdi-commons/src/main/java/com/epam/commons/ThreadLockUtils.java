package com.epam.commons;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.commons.linqinterfaces.JActionEx;
import com.epam.commons.linqinterfaces.JFuncR;
import com.epam.commons.linqinterfaces.JFuncREx;
import com.epam.commons.pairs.Pair;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by 12345 on 01.02.2016.
 */
public class ThreadLockUtils {
    private static final transient ReentrantLock LOCK = new ReentrantLock();

    public static void threadSafe(JAction action) {
        ReentrantLock lock = LOCK;
        lock.lock();
        action.invoke();
        lock.unlock();
    }

    public static <R> R threadSafe(JFuncR<R> func) {
        R result;
        ReentrantLock lock = LOCK;
        lock.lock();
        result = func.invoke();
        lock.unlock();
        return result;
    }

    public static void threadSafeException(JActionEx action) {
        ReentrantLock lock = LOCK;
        lock.lock();
        try { action.invoke();
        } catch (Exception ex) { throw new RuntimeException(ex.getMessage()); }
        lock.unlock();
    }

    public static <R> R threadSafeException(JFuncREx<R> func) {
        R result;
        ReentrantLock lock = LOCK;
        lock.lock();
        try { result = func.invoke();
        } catch (Exception ex) { throw new RuntimeException(ex.getMessage()); }
        lock.unlock();
        return result;
    }
}
