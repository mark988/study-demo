主要是演示dubbo 事务一致性问题
1.注册中心变更为 nacos 
2.两个服务之间的调用，当consumer事务失败或者调用者服务失败的时候，所有的事务都是失败状态

注意：
1. dubbo 注册服务 使用的依赖关系是：
       <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo-registry-nacos</artifactId>
            <version>0.0.1</version>
        </dependency>
2.使用seata事务一致 主要添加下面的内容就行：
       <dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-spring</artifactId>
            <version>${seata.version}</version>
        </dependency>

        <dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-dubbo-alibaba</artifactId>
            <version>${seata.version}</version>
        </dependency>
3.在springboot 框架里使用dubbo 添加一些内容即可:
       <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>0.2.0</version>
        </dependency>	
        当前依赖关系为 springboot2.1.4 使用0.2.0，如果单独升级com.alibaba.boot ,导致无法使用		