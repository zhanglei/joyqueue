package com.jd.journalq.client.internal.producer.callback;

import com.jd.journalq.client.internal.producer.domain.ProduceMessage;
import com.jd.journalq.client.internal.producer.domain.SendBatchResultData;

import java.util.List;
import java.util.Map;

/**
 * AsyncMultiBatchSendCallback
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2018/12/20
 */
public interface AsyncMultiBatchSendCallback {

    void onSuccess(Map<String, List<ProduceMessage>> messages, Map<String, SendBatchResultData> result);

    void onException(Map<String, List<ProduceMessage>> messages, Throwable cause);
}