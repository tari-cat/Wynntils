/*
 * Copyright © Wynntils 2026.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.features.embellishments;

import com.wynntils.core.consumers.features.Feature;
import com.wynntils.core.consumers.features.ProfileDefault;
import com.wynntils.core.persisted.Persisted;
import com.wynntils.core.persisted.config.Category;
import com.wynntils.core.persisted.config.Config;
import com.wynntils.core.persisted.config.ConfigCategory;
import com.wynntils.core.text.StyledText;
import com.wynntils.mc.event.SubtitleSetTextEvent;
import com.wynntils.models.character.event.CharacterDeathEvent;
import com.wynntils.models.characterstats.event.PowderSpecialEvent;
import com.wynntils.models.elements.type.Powder;
import com.wynntils.utils.mc.McUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.SubscribeEvent;

@ConfigCategory(Category.EMBELLISHMENTS)
public class ComedyFeature extends Feature {
    private static final Identifier DEATH_SOUND_ID = Identifier.fromNamespaceAndPath("wynntils", "comedy.death");
    private static final SoundEvent DEATH_SOUND = SoundEvent.createVariableRangeEvent(DEATH_SOUND_ID);

    private static final Identifier POWDER_SPECIAL_CHARGE_SOUND_ID =
            Identifier.fromNamespaceAndPath("wynntils", "comedy.powder_special_charge");
    private static final SoundEvent POWDER_SPECIAL_CHARGE =
            SoundEvent.createVariableRangeEvent(POWDER_SPECIAL_CHARGE_SOUND_ID);

    private static final Identifier POWDER_SPECIAL_ACTIVATE_SOUND_ID =
            Identifier.fromNamespaceAndPath("wynntils", "comedy.powder_special_activate");
    private static final SoundEvent POWDER_SPECIAL_ACTIVATE =
            SoundEvent.createVariableRangeEvent(POWDER_SPECIAL_ACTIVATE_SOUND_ID);

    @Persisted
    private final Config<Boolean> deathSound = new Config<>(false);

    @Persisted
    private final Config<Boolean> powderSpecialChargeSound = new Config<>(false);

    @Persisted
    private final Config<Boolean> powderSpecialActivateSound = new Config<>(false);

    @Persisted
    private final Config<Boolean> stotchMode = new Config<>(false);

    public ComedyFeature() {
        super(ProfileDefault.onlyDefault());
    }

    @SubscribeEvent
    public void onCharacterDeath(CharacterDeathEvent e) {
        if (deathSound.get()) {
            McUtils.playSoundMaster(DEATH_SOUND);
        }
    }

    @SubscribeEvent
    public void onPowderSpecial(PowderSpecialEvent e) {
        if (e.getPowderSpecialInfo().charge() >= 1f && powderSpecialChargeSound.get()) {
            McUtils.playSoundMaster(POWDER_SPECIAL_CHARGE);
        }
    }

    @SubscribeEvent
    public void onSubtitleChange(SubtitleSetTextEvent e) {
        StyledText title = StyledText.fromComponent(e.getComponent());

        Powder[] powders = Powder.values();

        String subtitle = title.getString();

        for (Powder p : powders) {
            if (subtitle.contains(p.getSpecialName())) {
                runPowderSpecialActivation();
            }
        }
    }

    public boolean isStotchModeEnabled() {
        return stotchMode.get();
    }

    private void runPowderSpecialActivation() {
        if (powderSpecialActivateSound.get()) {
            McUtils.playSoundMaster(POWDER_SPECIAL_ACTIVATE);
        }
    }
}
