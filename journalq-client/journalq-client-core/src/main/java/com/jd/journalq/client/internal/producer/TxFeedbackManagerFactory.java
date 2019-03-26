package com.jd.journalq.client.internal.producer;

import com.jd.journalq.client.internal.cluster.ClusterManager;
import com.jd.journalq.client.internal.cluster.ClusterManagerFactory;
import com.jd.journalq.client.internal.nameserver.NameServerConfig;
import com.jd.journalq.client.internal.nameserver.helper.NameServerHelper;
import com.jd.journalq.client.internal.producer.config.SenderConfig;
import com.jd.journalq.client.internal.producer.feedback.DefaultTxFeedbackManager;
import com.jd.journalq.client.internal.producer.feedback.TxFeedbackManagerWrapper;
import com.jd.journalq.client.internal.producer.feedback.config.TxFeedbackConfig;
import com.jd.journalq.client.internal.producer.transport.ProducerClientManager;
import com.jd.journalq.client.internal.producer.transport.ProducerClientManagerFactory;
import com.jd.journalq.client.internal.transport.config.TransportConfig;

/**
 * TxFeedbackManagerFactory
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2019/1/3
 */
public class TxFeedbackManagerFactory {

    public static TxFeedbackManager create(String address, String app, String token) {
        return create(address, app, token, null, null);
    }

    public static TxFeedbackManager create(String address, String app, String token, String region, String namespace) {
        TxFeedbackConfig txFeedbackConfig = new TxFeedbackConfig();
        txFeedbackConfig.setApp(app);
        NameServerConfig nameServerConfig = NameServerHelper.createConfig(address, app, token, region, namespace);
        return create(txFeedbackConfig, nameServerConfig);
    }

    public static TxFeedbackManager create(TxFeedbackConfig config, NameServerConfig nameServerConfig) {
        return create(config, nameServerConfig, new TransportConfig());
    }

    public static TxFeedbackManager create(TxFeedbackConfig config, NameServerConfig nameServerConfig, TransportConfig transportConfig) {
        ClusterManager clusterManager = ClusterManagerFactory.create(nameServerConfig, transportConfig);
        ProducerClientManager producerClientManager = ProducerClientManagerFactory.create(nameServerConfig, transportConfig);
        return create(config, nameServerConfig, clusterManager, producerClientManager);
    }

    public static TxFeedbackManager create(TxFeedbackConfig config, NameServerConfig nameServerConfig, ClusterManager clusterManager) {
        return create(config, nameServerConfig, new TransportConfig(), clusterManager);
    }

    public static TxFeedbackManager create(TxFeedbackConfig config, NameServerConfig nameServerConfig, TransportConfig transportConfig, ClusterManager clusterManager) {
        ProducerClientManager producerClientManager = ProducerClientManagerFactory.create(nameServerConfig, transportConfig);
        return create(config, nameServerConfig, clusterManager, producerClientManager);
    }

    public static TxFeedbackManager create(TxFeedbackConfig config, NameServerConfig nameServerConfig, ClusterManager clusterManager, ProducerClientManager producerClientManager) {
        MessageSender messageSender = MessageSenderFactory.create(producerClientManager, new SenderConfig());
        DefaultTxFeedbackManager txFeedbackManager = new DefaultTxFeedbackManager(config, nameServerConfig, clusterManager, messageSender);
        return new TxFeedbackManagerWrapper(null, null, messageSender, txFeedbackManager);
    }
}