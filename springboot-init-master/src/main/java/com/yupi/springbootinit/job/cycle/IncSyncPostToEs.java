//package com.yupi.springbootinit.job.cycle;
//
//import com.yupi.springbootinit.esdao.interfaceInfoEsDao;
//import com.yupi.springbootinit.mapper.interfaceInfoMapper;
//import com.yupi.springbootinit.model.dto.interfaceInfo.interfaceInfoEsDTO;
//import com.yupi.springbootinit.model.entity.interfaceInfo;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import javax.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.scheduling.annotation.Scheduled;
//
///**
// * 增量同步帖子到 es
// *
// * @author louis
// * 
// */
//// todo 取消注释开启任务
////@Component
//@Slf4j
//public class IncSyncinterfaceInfoToEs {
//
//    @Resource
//    private interfaceInfoMapper interfaceInfoMapper;
//
//    @Resource
//    private interfaceInfoEsDao interfaceInfoEsDao;
//
//    /**
//     * 每分钟执行一次
//     */
//    @Scheduled(fixedRate = 60 * 1000)
//    public void run() {
//        // 查询近 5 分钟内的数据
//        Date fiveMinutesAgoDate = new Date(new Date().getTime() - 5 * 60 * 1000L);
//        List<interfaceInfo> interfaceInfoList = interfaceInfoMapper.listinterfaceInfoWithDelete(fiveMinutesAgoDate);
//        if (CollectionUtils.isEmpty(interfaceInfoList)) {
//            log.info("no inc interfaceInfo");
//            return;
//        }
//        List<interfaceInfoEsDTO> interfaceInfoEsDTOList = interfaceInfoList.stream()
//                .map(interfaceInfoEsDTO::objToDto)
//                .collect(Collectors.toList());
//        final int pageSize = 500;
//        int total = interfaceInfoEsDTOList.size();
//        log.info("IncSyncinterfaceInfoToEs start, total {}", total);
//        for (int i = 0; i < total; i += pageSize) {
//            int end = Math.min(i + pageSize, total);
//            log.info("sync from {} to {}", i, end);
//            interfaceInfoEsDao.saveAll(interfaceInfoEsDTOList.subList(i, end));
//        }
//        log.info("IncSyncinterfaceInfoToEs end, total {}", total);
//    }
//}
