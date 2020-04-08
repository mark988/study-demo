1.引入pom.xml

        <dependency>
            <groupId>io.opentracing.contrib</groupId>
            <artifactId>opentracing-spring-jaeger-web-starter</artifactId>
            <version>3.1.1</version>
        </dependency>

2.配置 application

opentracing.jaeger.enabled=true
opentracing.jaeger.udp-sender.host=localhost
opentracing.jaeger.udp-sender.port=6831

3.docker 启动

    # 拉取jaeger镜像
    docker pull jaegertracing/all-in-one:1.17
    # 运行jaeger实例，
    docker run -d --name jaeger \
      -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 \
      -p 5775:5775/udp \
      -p 6831:6831/udp \
      -p 6832:6832/udp \
      -p 5778:5778 \
      -p 16686:16686 \
      -p 14268:14268 \
      -p 14250:14250 \
      -p 9411:9411 \
      jaegertracing/all-in-one:1.17

4.打开
    http://localhost/open
    http://localhost/tracing
    访问Jaeger UI界面http://localhost:16686/