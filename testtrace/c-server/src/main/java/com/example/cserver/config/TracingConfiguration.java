package com.example.cserver.config;

import brave.Tracing;
import brave.context.slf4j.MDCCurrentTraceContext;
import brave.http.HttpTracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

@Configuration
public class TracingConfiguration {
    @Bean
    Tracing tracing(@Value("${spring.application.name:spring-tracing}") String serviceName,
                    Reporter<Span> spanReporter) {
        return Tracing
                .newBuilder()
                .sampler(Sampler.ALWAYS_SAMPLE)
                .localServiceName(serviceName)
                .propagationFactory(ExtraFieldPropagation
                        .newFactory(B3Propagation.FACTORY, "client-id"))
                .currentTraceContext(MDCCurrentTraceContext.create())
                .spanReporter(spanReporter)
                .build();
    }

    @Bean
    HttpTracing httpTracing(Tracing tracing) {
        return HttpTracing.create(tracing);
    }
}
/*
@Configuration
@Import({
   TracingClientHttpRequestInterceptor.class,
    SpanCustomizingAsyncHandlerInterceptor.class
})
public class TracingConfiguration extends WebMvcConfigurerAdapter {

	
	@Bean
	public DelegatingTracingFilter getDelegatingTracingFilter() {
		return new DelegatingTracingFilter();
	}
	
  @Bean
  Sender sender() {
	  return OkHttpSender.create("http://127.0.0.1:9411/api/v2/spans");
  }


  @Bean
  AsyncReporter<Span> spanReporter() {
	  return AsyncReporter.create(sender());
  }

  @Bean
  Tracing tracing(@Value("${zipkin.service:brave-webmvc-example}") String serviceName) {
	  return Tracing.newBuilder()
        .localServiceName(serviceName)
        .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
        .spanReporter(spanReporter()).currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder()
                      .addScopeDecorator(MDCScopeDecorator.create()).build()).build();
  }

  @Bean
  HttpTracing httpTracing(Tracing tracing) {
	  return HttpTracing.create(tracing);
  }

 
  
  @Autowired
  TracingClientHttpRequestInterceptor clientInterceptor;

  @Bean
  public RestTemplate restTemplate() {
	  RestTemplate restTemplate = new RestTemplate();
	  List<ClientHttpRequestInterceptor> interceptors =new ArrayList<>(restTemplate.getInterceptors());
	  interceptors.add(clientInterceptor);
	  restTemplate.setInterceptors(interceptors);
	  return restTemplate;
  }

  @Autowired
  SpanCustomizingAsyncHandlerInterceptor serverInterceptor;

  @Override 
  public void addInterceptors(InterceptorRegistry registry) {
	  registry.addInterceptor(serverInterceptor);
  }
}*/
