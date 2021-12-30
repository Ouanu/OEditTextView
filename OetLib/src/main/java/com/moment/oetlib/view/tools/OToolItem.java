package com.moment.oetlib.view.tools;


import com.moment.oetlib.view.OEditText;

public abstract class OToolItem {
    private final OEditText oetText;

    public abstract void applyOMDTool();
    public abstract void setStyle();

    public OToolItem(OEditText oetText) {
        this.oetText = oetText;
    }

    public OEditText getOetText() {
        return oetText;
    }


}
