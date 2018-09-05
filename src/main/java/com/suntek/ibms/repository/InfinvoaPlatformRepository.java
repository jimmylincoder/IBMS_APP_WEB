package com.suntek.ibms.repository;

import com.suntek.ibms.domain.InfinvoaPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 英飞平台仓库
 *
 * @author jimmy
 */
public interface InfinvoaPlatformRepository extends JpaRepository<InfinvoaPlatform,String>
{
}
