package com.granatasoft.remotelist.internal;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.context.IBasePackageDefinition;

@Immutable
public class GranatasoftBasePackageDefinition implements IBasePackageDefinition {

    @Override
    public String getBasePackage() {
        return "com.granatasoft";
    }

}
