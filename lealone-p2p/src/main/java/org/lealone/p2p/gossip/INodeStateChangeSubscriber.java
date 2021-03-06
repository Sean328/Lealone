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
package org.lealone.p2p.gossip;

import org.lealone.net.NetNode;

/**
 * This is called by an instance of the INodeStateChangePublisher to notify
 * interested parties about changes in the the state associated with any node.
 * For instance if node A figures there is a changes in state for an node B
 * it notifies all interested parties of this change. It is upto to the registered
 * instance to decide what he does with this change. Not all modules maybe interested
 * in all state changes.
 */
public interface INodeStateChangeSubscriber {
    /**
     * Use to inform interested parties about the change in the state
     * for specified node
     *
     * @param node node for which the state change occurred.
     * @param epState  state that actually changed for the above node.
     */
    public default void onJoin(NetNode node, NodeState epState) {
    }

    public default void beforeChange(NetNode node, NodeState currentState, ApplicationState newStateKey,
            VersionedValue newValue) {
    }

    public default void onChange(NetNode node, ApplicationState state, VersionedValue value) {
    }

    public default void onAlive(NetNode node, NodeState state) {
    }

    public default void onDead(NetNode node, NodeState state) {
    }

    public default void onRemove(NetNode node) {
    }

    /**
     * Called whenever a node is restarted.
     * Note that there is no guarantee when that happens that the node was
     * previously marked down. It will have only if {@code state.isAlive() == false}
     * as {@code state} is from before the restarted node is marked up.
     */
    public default void onRestart(NetNode node, NodeState state) {
    }
}
