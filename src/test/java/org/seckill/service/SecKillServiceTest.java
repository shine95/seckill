package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatkillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})

public class SecKillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillService secKillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = secKillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000L;
        Seckill seckill = secKillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    //测试代码完整逻辑，注意可重复执行。
    @Test
    public void testSeckillLogic() throws Exception {
        long id = 1003;
        Exposer exposer = secKillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 138123456789L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution executeseckill = secKillService.executeseckill(id, phone, md5);
                logger.info("result={}", executeseckill);
            } catch (RepeatkillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e1) {
                logger.error(e1.getMessage());
            }
        }else{
            //秒杀为开启
            logger.warn("exposer={}",exposer);
        }
    }

//    @Test
//    public void exportSeckillUrl() throws Exception {
//        long id =1000L;
//        Exposer exposer = secKillService.exportSeckillUrl(id);
//        logger.info("exposer={}",exposer);
//    }
//
//    @Test
//    public void executeseckill() throws Exception {
//        long id =1000;
//        long phone=13888888869L;
//        String md5 = "2c806ba4f6bee0d3d5e58d831c392110";
//        try {
//            SeckillExecution executeseckill = secKillService.executeseckill(id, phone, md5);
//            logger.info("result={}",executeseckill);
//        } catch (RepeatkillException e) {
//           logger.error(e.getMessage());
//        } catch (SeckillCloseException e1) {
//            logger.error(e1.getMessage());
//        }
//    }

}