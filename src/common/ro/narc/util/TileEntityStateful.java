package ro.narc.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityStateful extends TileEntity {
    public static Byte statePacketID = null;
    public static String channelName = null;

    @Override
    public Packet getDescriptionPacket() {
        assert statePacketID != null: "Bad NarcLib implementation: no packet ID for this " + this;
        assert channelName != null: "Bad NarcLib implementation: no channel name for this " + this;

        return (new PacketTileEntityState(this)).getPacket250(statePacketID, channelName);
    }

    public abstract void readFromNetwork(DataInput data) throws IOException;
    public abstract void writeToNetwork(DataOutput data) throws IOException;
}
