package com.oli.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

/**
 * Author: Oliver
 */
@Slf4j
@Rollback
@Transactional
public abstract class ServiceTestAbstract<T, ID> implements ServiceTestInterface<T, ID> {
    protected abstract T getTestEntity();
}
