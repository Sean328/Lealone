/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lealone.p2p.gossip.handler;

import org.lealone.common.logging.Logger;
import org.lealone.common.logging.LoggerFactory;
import org.lealone.p2p.gossip.FailureDetector;
import org.lealone.p2p.gossip.Gossiper;
import org.lealone.p2p.gossip.protocol.GossipShutdown;
import org.lealone.p2p.gossip.protocol.P2pPacketIn;

public class GossipShutdownHandler implements P2pPacketHandler<GossipShutdown> {

    private static final Logger logger = LoggerFactory.getLogger(GossipShutdownHandler.class);

    @Override
    public void handle(P2pPacketIn<GossipShutdown> packetIn, int id) {
        if (!Gossiper.instance.isEnabled()) {
            if (logger.isDebugEnabled())
                logger.debug("Ignoring shutdown message from {} because gossip is disabled", packetIn.from);
            return;
        }
        FailureDetector.instance.forceConviction(packetIn.from);
    }
}