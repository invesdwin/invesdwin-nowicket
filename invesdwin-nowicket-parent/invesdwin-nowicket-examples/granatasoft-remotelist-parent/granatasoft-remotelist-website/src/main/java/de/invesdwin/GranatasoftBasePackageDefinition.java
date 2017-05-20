package de.invesdwin;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import de.invesdwin.context.beans.init.locations.IBasePackageDefinition;

@Immutable
@Named
public class GranatasoftBasePackageDefinition implements IBasePackageDefinition {

    @Override
    public String getBasePackage() {
        return "com.granatasoft";
    }

}
