/*
 * Copyright © Wynntils 2024.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.models.characterstats.type;

import com.wynntils.models.elements.type.Powder;

import java.util.Objects;

public record PowderSpecialInfo(float charge, Powder powder) {
    public static final PowderSpecialInfo EMPTY = new PowderSpecialInfo(0, Powder.EARTH);

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PowderSpecialInfo info) {
            return info.powder() == powder() && (Math.abs(info.charge() - charge()) < 0.01f);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(charge, powder);
    }
}
