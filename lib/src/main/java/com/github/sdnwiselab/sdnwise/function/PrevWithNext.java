/* 
 * Copyright (C) 2015 SDN-WISE
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.sdnwiselab.sdnwise.function;

import com.github.sdnwiselab.sdnwise.flowtable.FlowTableEntry;
import com.github.sdnwiselab.sdnwise.packet.GeoDataPacket;
import com.github.sdnwiselab.sdnwise.packet.NetworkPacket;
import com.github.sdnwiselab.sdnwise.util.Neighbor;
import com.github.sdnwiselab.sdnwise.util.NodeAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Sebastiano Milardo
 */
public class PrevWithNext implements FunctionInterface {

    @Override
    public void function(
            HashMap<String, Object> adcRegister, 
            ArrayList<FlowTableEntry> flowTable, 
            ArrayList<Neighbor> neighborTable, 
            int[] statusRegister, 
            ArrayList<NodeAddress> acceptedId, 
            ArrayBlockingQueue<int[]> flowTableQueue,
            ArrayBlockingQueue<int[]> txQueue,
            int value,
            NetworkPacket np
    ) {    
        GeoDataPacket gp = (GeoDataPacket)np;
        gp.setPreviousMulticastNodeAddress(gp.getCurrentMulticastNodeAddress());
        gp.setCurrentMulticastNodeAddress(new NodeAddress(value));
        flowTableQueue.add(gp.toIntArray());
    }   
}