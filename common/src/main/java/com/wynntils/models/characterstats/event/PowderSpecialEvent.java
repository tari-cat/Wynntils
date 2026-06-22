/*
 * Copyright © Wynntils 2026.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.models.characterstats.event;

import com.wynntils.models.characterstats.type.PowderSpecialInfo;
import net.neoforged.bus.api.Event;

public class PowderSpecialEvent extends Event {
    private final PowderSpecialInfo powderSpecialInfo;

    public PowderSpecialEvent(PowderSpecialInfo info) {
        powderSpecialInfo = info;
    }

    public PowderSpecialInfo getPowderSpecialInfo() {
        return powderSpecialInfo;
    }
}
