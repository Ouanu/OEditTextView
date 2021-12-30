package com.moment.myview.view.tools;

import com.moment.myview.view.OMDEditText;

public abstract class OMDToolItem {
    private final OMDEditText oetText;

    public abstract void applyOMDTool();
    public abstract void setStyle();

    public OMDToolItem(OMDEditText oetText) {
        this.oetText = oetText;
    }

    public OMDEditText getOetText() {
        return oetText;
    }


}
