package com.granatasoft.remotelist.internal;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.context.beans.hook.IBasePackageDefinitionHook;

@Immutable
public class GranatasoftBasePackageDefinitionHook implements IBasePackageDefinitionHook {

    @Override
    public String getBasePackage() {
        return "com.granatasoft";
    }

}
