/**
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
package com.jd.joyqueue.broker.protocol.network;

import com.jd.joyqueue.broker.BrokerContext;
import com.jd.joyqueue.broker.BrokerContextAware;
import com.jd.joyqueue.broker.protocol.JoyQueueConsts;
import com.jd.joyqueue.broker.protocol.JoyQueueContext;
import com.jd.joyqueue.broker.protocol.config.JoyQueueConfig;
import com.jd.joyqueue.broker.protocol.coordinator.Coordinator;
import com.jd.joyqueue.broker.protocol.coordinator.GroupMetadataManager;
import com.jd.joyqueue.broker.protocol.coordinator.assignment.PartitionAssignmentHandler;
import com.jd.joyqueue.broker.protocol.network.helper.JoyQueueProtocolHelper;
import com.jd.joyqueue.broker.polling.LongPollingManager;
import com.jd.joyqueue.network.protocol.ExceptionHandlerProvider;
import com.jd.joyqueue.network.protocol.ProtocolService;
import com.jd.joyqueue.network.transport.codec.CodecFactory;
import com.jd.joyqueue.network.transport.command.handler.CommandHandlerFactory;
import com.jd.joyqueue.network.transport.command.handler.ExceptionHandler;
import com.jd.joyqueue.toolkit.service.Service;
import io.netty.buffer.ByteBuf;

/**
 * JoyQueueProtocol
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2018/11/27
 */
public class JoyQueueProtocol extends Service implements ProtocolService, BrokerContextAware, ExceptionHandlerProvider {

    private JoyQueueConfig config;
    private Coordinator coordinator;
    private GroupMetadataManager coordinatorGroupManager;
    private PartitionAssignmentHandler partitionAssignmentHandler;
    private LongPollingManager longPollingManager;
    private JoyQueueContext joyQueueContext;

    @Override
    public void setBrokerContext(BrokerContext brokerContext) {
        this.config = new JoyQueueConfig(brokerContext.getPropertySupplier());

        this.coordinator = new Coordinator(brokerContext.getCoordinatorService().getCoordinator());
        this.coordinatorGroupManager = new GroupMetadataManager(config, brokerContext.getCoordinatorService().getOrCreateGroupMetadataManager(JoyQueueConsts.COORDINATOR_NAMESPACE));
        this.partitionAssignmentHandler = new PartitionAssignmentHandler(config, coordinatorGroupManager);
        this.longPollingManager = new LongPollingManager(brokerContext.getSessionManager(), brokerContext.getClusterManager(), brokerContext.getConsume(),brokerContext.getPropertySupplier());

        this.joyQueueContext = new JoyQueueContext(config, coordinator, coordinatorGroupManager, partitionAssignmentHandler, longPollingManager, brokerContext);
    }

    @Override
    protected void doStart() throws Exception {
        partitionAssignmentHandler.start();
        longPollingManager.start();
    }

    @Override
    protected void doStop() {
        partitionAssignmentHandler.stop();
        longPollingManager.stop();
    }

    @Override
    public boolean isSupport(ByteBuf buffer) {
        return JoyQueueProtocolHelper.isSupport(buffer);
    }

    @Override
    public CodecFactory createCodecFactory() {
        return new JoyQueueCodecFactory();
    }

    @Override
    public CommandHandlerFactory createCommandHandlerFactory() {
        return new JoyQueueCommandHandlerFactory(joyQueueContext);
    }

    @Override
    public String type() {
        return JoyQueueConsts.PROTOCOL_TYPE;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new JoyQueueExceptionHandler();
    }
}