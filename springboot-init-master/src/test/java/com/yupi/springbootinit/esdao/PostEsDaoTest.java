package com.yupi.springbootinit.esdao;

import com.yupi.springbootinit.model.dto.interfaceInfo.interfaceInfoEsDTO;
import com.yupi.springbootinit.model.dto.interfaceInfo.interfaceInfoQueryRequest;
import com.yupi.springbootinit.model.entity.interfaceInfo;
import com.yupi.springbootinit.service.interfaceInfoService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 帖子 ES 操作测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
public class interfaceInfoEsDaoTest {

    @Resource
    private interfaceInfoEsDao interfaceInfoEsDao;

    @Resource
    private interfaceInfoService interfaceInfoService;

    @Test
    void test() {
        interfaceInfoQueryRequest interfaceInfoQueryRequest = new interfaceInfoQueryRequest();
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<interfaceInfo> page =
                interfaceInfoService.searchFromEs(interfaceInfoQueryRequest);
        System.out.println(page);
    }

    @Test
    void testSelect() {
        System.out.println(interfaceInfoEsDao.count());
        Page<interfaceInfoEsDTO> interfaceInfoPage = interfaceInfoEsDao.findAll(
                PageRequest.of(0, 5, Sort.by("createTime")));
        List<interfaceInfoEsDTO> interfaceInfoList = interfaceInfoPage.getContent();
        System.out.println(interfaceInfoList);
    }

    @Test
    void testAdd() {
        interfaceInfoEsDTO interfaceInfoEsDTO = new interfaceInfoEsDTO();
        interfaceInfoEsDTO.setId(1L);
        interfaceInfoEsDTO.setTitle("test");
        interfaceInfoEsDTO.setContent("test");
        interfaceInfoEsDTO.setTags(Arrays.asList("java", "python"));
        interfaceInfoEsDTO.setThumbNum(1);
        interfaceInfoEsDTO.setFavourNum(1);
        interfaceInfoEsDTO.setUserId(1L);
        interfaceInfoEsDTO.setCreateTime(new Date());
        interfaceInfoEsDTO.setUpdateTime(new Date());
        interfaceInfoEsDTO.setIsDelete(0);
        interfaceInfoEsDao.save(interfaceInfoEsDTO);
        System.out.println(interfaceInfoEsDTO.getId());
    }

    @Test
    void testFindById() {
        Optional<interfaceInfoEsDTO> interfaceInfoEsDTO = interfaceInfoEsDao.findById(1L);
        System.out.println(interfaceInfoEsDTO);
    }

    @Test
    void testCount() {
        System.out.println(interfaceInfoEsDao.count());
    }

    @Test
    void testFindByCategory() {
        List<interfaceInfoEsDTO> interfaceInfoEsDaoTestList = interfaceInfoEsDao.findByUserId(1L);
        System.out.println(interfaceInfoEsDaoTestList);
    }
}
