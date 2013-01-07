package ro.narc.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

public class PacketTileEntityState {
    public TileEntityStateful machine;

    public PacketTileEntityState(TileEntityStateful machine) {
        this.machine = machine;
    }

    public Packet getPacket250(byte packetID, String channelName) {
        ByteArrayDataOutput data = ByteStreams.newDataOutput();

        try {
            data.writeByte(packetID);
            data.writeInt(machine.xCoord);
            data.writeInt(machine.yCoord);
            data.writeInt(machine.zCoord);
            machine.writeToNetwork(data);
        }
        catch(IOException e) {
            return null; // shit happens, deal with it
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = channelName;
        packet.data = data.toByteArray();
        packet.length = packet.data.length;
        packet.isChunkDataPacket = true;

        if(NarcLib.isDebugging()) {
            System.out.println("wrote TileEntity state packet for " + machine + ": " + Arrays.toString(data.toByteArray()));
        }

        return packet;
    }

    public static void readPacket250(DataInput data) {
        try {
            int x = data.readInt();
            int y = data.readInt();
            int z = data.readInt();

            World world = NarcLib.getClientWorld();
            assert world != null: "The world was null! You are not on the client, but you got a client-side packet!";

            TileEntityStateful machine = (TileEntityStateful)world.getBlockTileEntity(x, y, z);
            if(machine == null) {
                return;
            }
            machine.readFromNetwork(data);

            if(NarcLib.isDebugging()) {
                System.out.println("read TileEntity state packet for " + machine);
            }
        }
        catch(IOException e) {
            // and completely ignore it.
        }
    }
}
