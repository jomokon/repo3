package cn.jpa.test;

import cn.jpa.dao.CustomerDao;
import cn.jpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Random;

/**
 *
 */

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration("classpath:applicationContext.xml") //指定spring容器的配置信息
public class JpaTest {

    /*
     *
     *
     *
     * */

    @Test
    public void test() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("小鱼");
        customer.setCustLeave("2");
        customer.setCustAddress("噜噜噜");

        entityManager.persist(customer);

        tx.commit();

        entityManager.close();
        factory.close();

    }

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSpring() {

        Customer user = this.customerDao.findOne(1L);
        user.setCustId(11L);
        user.setCustName("zhangsaj");
        this.customerDao.save(user);

        this.customerDao.exists(3L);


    }

    @Test
    public void testTime() {

        long c1 = 0;
        long c2 = 0;
        long c3 = 0;
        long time = new Date().getTime();
        Random random = new Random();
        for (long i = 0; i < 1000000000; i++) {
            int a = random.nextInt(101);
            if (a % 2 == 0) {
                c1++;
            } else {
                c2++;
            }
            if (a == 101) {
                c3++;
            }
        }
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        System.out.println(new Date().getTime()-time);

        int i = 1;

        i++;

    }


}
