package com.account.config;

import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2SecurityUtil;
import io.grpc.netty.shaded.io.netty.handler.ssl.*;
import io.grpc.netty.shaded.io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.SneakyThrows;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ChannelConfig {
    @Primary
    @Bean
    public GrpcChannelConfigurer keepAliveClientConfigurer() {
        return (channelBuilder, name) -> {
            if (channelBuilder instanceof NettyChannelBuilder) {
                ((NettyChannelBuilder) channelBuilder)
                        .sslContext(getSslContext())
                        .negotiationType(NegotiationType.TLS);
            }
        };
    }

    @Bean
    @SneakyThrows
    public SslContext getSslContext()  {
        return SslContextBuilder.forClient()
                .sslProvider(SslProvider.JDK)
                .ciphers(Http2SecurityUtil.CIPHERS, SupportedCipherSuiteFilter.INSTANCE)
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .applicationProtocolConfig(
                        new ApplicationProtocolConfig(ApplicationProtocolConfig.Protocol.ALPN, ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE,
                                ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT, ApplicationProtocolNames.HTTP_2))
                .build();
    }

}
