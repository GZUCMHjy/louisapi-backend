package com.yupi.springbootinit.job.once;

import com.yupi.springbootinit.esdao.PostEsDao;
import com.yupi.springbootinit.esdao.PostEsDao;
import com.yupi.springbootinit.service.PostService;
import com.yupi.springbootinit.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.CommandLineRunner;

/**
 * 全量同步帖子到 es（首次）
 *
 * @author louis
 *
 */
// todo 取消注释开启任务(Main方法启动 就执行一次任务)
//@Component
@Slf4j
public class FullSyncPostToEs implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }
}
