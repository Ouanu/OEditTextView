package com.moment.oetlib.view.tools;

import android.content.ContentResolver;

import com.moment.oetlib.view.OEditText;

import java.util.ArrayList;
import java.util.List;

public class OTools {
    private final List<OToolItem> toolItemList = new ArrayList<>();
    private OEditText oetText;
    private ContentResolver resolver;

    public OTools(OEditText oetText, ContentResolver resolver) {
        this.oetText = oetText;
        this.resolver = resolver;
    }

    /*
    仅供测试所有功能时使用
     */
    public void autoTool() {
        OBoldTool boldTool = new OBoldTool(oetText);
        OItalyTool italyTool = new OItalyTool(oetText);
        OTitleTool titleTool = new OTitleTool(oetText);
        OListTool listTool = new OListTool(oetText);
        toolItemList.add(boldTool);
        toolItemList.add(italyTool);
        toolItemList.add(titleTool);
        toolItemList.add(listTool);
    }

    public void addToolItem(OToolItem toolItem) {
        toolItemList.add(toolItem);
    }

    public List<OToolItem> getToolList() {
        return toolItemList;
    }
}
