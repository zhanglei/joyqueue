/**
 * Copyright 2019 The JoyQueue Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.openmessaging.spring.boot.adapter;

import io.openmessaging.consumer.MessageListener;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.message.Message;

import java.lang.reflect.Method;

/**
 * Adapter for the MessageListener.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public class MessageListenerReflectAdapter implements MessageListener {

    private Object instance;
    private Method method;

    public MessageListenerReflectAdapter(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
        this.method.setAccessible(true);
    }

    @Override
    public void onReceived(Message message, Context context) {
        try {
            method.invoke(instance, message, context);
        } catch (Exception e) {
            throw new OMSRuntimeException(-1, e.getMessage(), e);
        }
    }
}