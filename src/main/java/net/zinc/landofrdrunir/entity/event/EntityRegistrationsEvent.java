package net.zinc.landofrdrunir.entity.event;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zinc.landofrdrunir.LandOfDrunir;
import net.zinc.landofrdrunir.entity.client.deer.DeerModel;
import net.zinc.landofrdrunir.entity.client.deer.DeerSaddleModel;
import net.zinc.landofrdrunir.entity.client.stoneling.StonelingModel;
import net.zinc.landofrdrunir.entity.server.custom.deer.Deer;
import net.zinc.landofrdrunir.entity.server.custom.stoneling.StonelingEntity;
import net.zinc.landofrdrunir.registry.LODEntities;

@Mod.EventBusSubscriber(modid = LandOfDrunir.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegistrationsEvent {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(StonelingModel.LAYER_LOCATION, StonelingModel::createBodyLayer);
        event.registerLayerDefinition(DeerModel.LAYER_LOCATION, DeerModel::createBodyLayer);
        event.registerLayerDefinition(DeerSaddleModel.LAYER_LOCATION, DeerSaddleModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(LODEntities.STONELING.get(), StonelingEntity.createAttributes().build());
        event.put(LODEntities.DEER.get(), Deer.createAttributes().build());
    }
}
