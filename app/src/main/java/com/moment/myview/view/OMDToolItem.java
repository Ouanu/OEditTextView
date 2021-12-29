package com.moment.myview.view;

public abstract class OMDToolItem {
    private OMDEditText oetText;
    public abstract void applyOMDTool();

    public OMDToolItem(OMDEditText oetText) {
        this.oetText = oetText;
    }

    public OMDEditText getOetText() {
        return oetText;
    }
}
