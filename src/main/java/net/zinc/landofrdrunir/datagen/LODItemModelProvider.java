package net.zinc.landofrdrunir.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zinc.landofrdrunir.LandOfDrunir;
import net.zinc.landofrdrunir.registry.LODItems;

public class LODItemModelProvider extends ItemModelProvider {
    public LODItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LandOfDrunir.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(LODItems.ANTLERS.get());
        basicItem(LODItems.FLORA_ANTLERS.get());
        basicItem(LODItems.RAW_DEER_MEAT.get());
        basicItem(LODItems.COOKED_DEER_MEAT.get());
    }
}
