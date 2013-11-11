package org.spoutcraft.mod.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.block.material.Material;
import net.minecraft.network.INetworkManager;
import org.spoutcraft.api.PrefabRegistry;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.material.MaterialPrefab;
import org.spoutcraft.mod.protocol.SpoutcraftPacket;
import org.spoutcraft.mod.protocol.message.UpdatePrefabMessage;

public class SpoutcraftMaterialPrefabRegistry implements PrefabRegistry<MaterialPrefab, Material> {
	private static final ArrayList<MaterialPrefab> REGISTRY = new ArrayList<>();
	//INTERNAL
	private static final HashMap<MaterialPrefab, Material> PREFAB_BY_MATERIAL = new HashMap<>();

	@Override
	public MaterialPrefab put(MaterialPrefab prefab) {
		create(prefab);
		return prefab;
	}

	@Override
	public Material create(MaterialPrefab prefab) {
		if (prefab == null) {
			throw new IllegalStateException("Attempt made to put null material prefab into registry!");
		}

		final Material material = new CustomMaterial(prefab);
		REGISTRY.add(prefab);
		PREFAB_BY_MATERIAL.put(prefab, material);

		//TODO Materials need to be registered?
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketDispatcher.sendPacketToAllPlayers(new SpoutcraftPacket(new UpdatePrefabMessage(prefab)));
		}
		return material;
	}

	@Override
	public MaterialPrefab get(String identifier) {
		for (MaterialPrefab prefab : REGISTRY) {
			if (prefab.getIdentifier().equals(identifier)) {
				return prefab;
			}
		}
		return null;
	}

	@Override
	public Material find(MaterialPrefab prefab) {
		return prefab == null ? null : PREFAB_BY_MATERIAL.get(prefab);
	}

	@Override
	public Material find(String identifier) {
		if (identifier != null && !identifier.isEmpty()) {
			for (Map.Entry<MaterialPrefab, Material> entry : PREFAB_BY_MATERIAL.entrySet()) {
				if (entry.getKey().getIdentifier().equals(identifier)) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * Syncs the entire block registry to the client
	 *
	 * @param network The connected network
	 */
	public void sync(final INetworkManager network) {
		Spoutcraft.getLogger().info("Preparing to sync material registry");
		//TODO Scheduler and sending
		for (MaterialPrefab prefab : REGISTRY) {
			Spoutcraft.getLogger().info("Syncing material prefab to client");
			Spoutcraft.getLogger().info(prefab.toString());
			network.addToSendQueue(new SpoutcraftPacket(new UpdatePrefabMessage(prefab)));
		}
	}
}