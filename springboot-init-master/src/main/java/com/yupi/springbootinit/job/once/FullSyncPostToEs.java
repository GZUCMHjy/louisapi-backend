//package com.yupi.springbootinit.job.once;
//
//import com.yupi.springbootinit.esdao.interfaceInfoEsDao;
//import com.yupi.springbootinit.model.dto.interfaceInfo.interfaceInfoEsDTO;
//import com.yupi.springbootinit.model.entity.interfaceInfo;
//import com.yupi.springbootinit.service.interfaceInfoService;
//import java.util.List;
//import java.util.stream.Collectors;
//import javax.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.boot.CommandLineRunner;
//
///**
// * 全量同步帖子到 es
// *
// * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
// * @from <a href="https://yupi.icu">编程导航知识星球</a>
// */
//// todo 取消注释开启任务
////@Component
//@Slf4j
//public class FullSyncinterfaceInfoToEs implements CommandLineRunner {
//
//    @Resource
//    private interfaceInfoService interfaceInfoService;
//
//    @Resource
//    private interfaceInfoEsDao interfaceInfoEsDao;
//
//    @Override
//    public void run(String... args) {
//        List<interfaceInfo> interfaceInfoList = interfaceInfoService.list();
//        if (CollectionUtils.isEmpty(interfaceInfoList)) {
//            return;
//        }
//        List<interfaceInfoEsDTO> interfaceInfoEsDTOList = interfaceInfoList.stream().map(interfaceInfoEsDTO::objToDto).collect(Collectors.toList());
//        final int pageSize = 500;
//        int total = interfaceInfoEsDTOList.size();
//        log.info("FullSyncinterfaceInfoToEs start, total {}", total);
//        for (int i = 0; i < total; i += pageSize) {
//            int end = Math.min(i + pageSize, total);
//            log.info("sync from {} to {}", i, end);
//            interfaceInfoEsDao.saveAll(interfaceInfoEsDTOList.subList(i, end));
//        }
//        log.info("FullSyncinterfaceInfoToEs end, total {}", total);
//    }
//}
