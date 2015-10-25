/*
 * Copyright 2015 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.mazes;

import org.terasology.math.geom.Vector3i;

import java.util.HashMap;

/**
 * Created by john on 10/19/15.
 */
public class UpStairs extends RoomTemplate {
    public UpStairs(MazeInfo maze_info_input) {
        super(maze_info_input);
        // TODO: make it so this code is only executed once and other stairs are copies.
        // Spiral clockwise from the lower right hand corner, going down one block per level
        Vector3i focus = new Vector3i(0,0,0);
        room_blocks.put(new Vector3i(focus), false);
        Vector3i direction = Vector3i.west();
        while (focus.y() < maze_info.room_height + maze_info.floor_thickness) {
            spiralDown(focus,direction);
            room_blocks.put(new Vector3i(focus), false);
        }
    }
    public boolean containsRelativePosition(Vector3i position)
    {
        // Everything below the floor is solid
        if (position.y() > maze_info.room_height)
            return false;

        // Otherwise, we use a hash map to create and use a stair case block.
        if (room_blocks.containsKey(position))
        {
            return room_blocks.get(position);
        }
        else
        {
            // Absence from the map indicates an air block
            return true;
        }
    }
}
