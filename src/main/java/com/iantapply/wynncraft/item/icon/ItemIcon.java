package com.iantapply.wynncraft.item.icon;

import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;

@Getter
public class ItemIcon {
    private final ItemIconFormat format;
    private String value;
    private ItemIconAttributeValue attributeValue;

    /**
     * Used for creating item icons with a format and value
     * @param format The format of the item icon
     * @param value The value of the item icon
     */
    public ItemIcon(ItemIconFormat format, String value) {
        if (format == null || format == ItemIconFormat.ATTRIBUTE) {
            Logger.log(LoggingLevel.ERROR, "Invalid format parsed for item icon with only value provided: " + format);
        }

        this.format = format;
        this.value = value;
    }

    /**
     * Used for creating item icons with a format and attribute values
     * @param format The format of the item icon
     * @param value The attribute value of the item icon
     */
    public ItemIcon(ItemIconFormat format, ItemIconAttributeValue value) {
        if (format != ItemIconFormat.ATTRIBUTE) {
            Logger.log(LoggingLevel.ERROR, "Invalid format parsed for item icon with only attribute value provided: " + format);
        }

        this.format = format;
        this.attributeValue = value;
    }
}
